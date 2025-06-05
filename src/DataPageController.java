import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DataPageController {

    int nimMahasiswa;
    int angkatanMahasiswa;
    String namaMahasiswa;
    String kelasMahasiswa;
    String genderMahasiswa = null;
    String jurusanMahasiswa;
    boolean checkBox;

    @FXML
    private TableColumn<Mahasiswa, Integer> NIM;

    @FXML
    private TableColumn<Mahasiswa, Integer> angkatan;

    @FXML
    private TableColumn<Mahasiswa, String> kelas;

    @FXML
    private TableColumn<Mahasiswa, String> nama;

    @FXML
    private TableColumn<Mahasiswa, String> jurusan;

    @FXML
    private TableColumn<Mahasiswa, String> gender;

    @FXML
    private TableView<Mahasiswa> tableDataMahasiswa;

    @FXML
    private TextField tfAngkatan;

    @FXML
    private TextField tfKelas;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfNim;

    @FXML
    private CheckBox verifikasiData;

    @FXML
    private RadioButton perempuan;

    @FXML
    private RadioButton lakilaki;

    @FXML
    private ChoiceBox<String> cbJurusan;

    ObservableList<Mahasiswa> dataMahasiswa = FXCollections.observableArrayList();

    ObservableList<String> dataJurusan = FXCollections.observableArrayList("Pilih jurusan", "Sistem Informasi",
            "Teknik Industri",
            "Informatika", "Teknik Elektro");

    @FXML
    void addData(ActionEvent event) {

        try {
            nimMahasiswa = Integer.valueOf(tfNim.getText());
            namaMahasiswa = tfNama.getText();
            kelasMahasiswa = tfKelas.getText();
            angkatanMahasiswa = Integer.valueOf(tfAngkatan.getText());
            if (lakilaki.isSelected()) {
                genderMahasiswa = lakilaki.getText();
            }

            if (perempuan.isSelected()) {
                genderMahasiswa = perempuan.getText();
            }

            jurusanMahasiswa = cbJurusan.getValue();

            checkBox = verifikasiData.isSelected();

            Mahasiswa mahasiswa = new Mahasiswa(nimMahasiswa, angkatanMahasiswa, namaMahasiswa, kelasMahasiswa,
                    genderMahasiswa, jurusanMahasiswa);

            if (checkBox && !jurusanMahasiswa.equals("Pilih jurusan") && genderMahasiswa != null) {
                if (dataMahasiswa.isEmpty()) {
                    dataMahasiswa.add(mahasiswa);
                    clearData();
                } else {
                    for (Mahasiswa i : dataMahasiswa) {
                        if (i.getNIM() == nimMahasiswa) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERROR");
                            alert.setContentText("NIM tersebut sudah ada");
                            alert.showAndWait();
                        } else {
                            dataMahasiswa.add(mahasiswa);
                            clearData();
                            break;
                        }
                    }
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Apakah anda yakin data tersebut benar? silahkan cek kembali");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Kesalahan dalam input data");
            alert.setContentText("Silahkan cek data yang anda masukan");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteData(ActionEvent event) {
        // Gunakan try and catch jika gagal menghapus data
        try {
            // Menggunakan looping foreach untuk mengecek dimana posisi data mahasiswa
            for (Mahasiswa i : dataMahasiswa) {
                if (i.getNIM() == nimMahasiswa) {
                    // Menghapus data dari dataMahasiswa menggunakan posisi dari data mahasiswa
                    dataMahasiswa.remove(i);
                    clearData();
                    break;
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Gagal menghapus data");
            alert.showAndWait();
        }
    }

    @FXML
    void editData(ActionEvent event) {

        int index = 0;

        try {
            nimMahasiswa = Integer.valueOf(tfNim.getText());
            namaMahasiswa = tfNama.getText();
            kelasMahasiswa = tfKelas.getText();
            angkatanMahasiswa = Integer.valueOf(tfAngkatan.getText());

            if (lakilaki.isSelected()) {
                genderMahasiswa = lakilaki.getText();
            }

            if (perempuan.isSelected()) {
                genderMahasiswa = perempuan.getText();
            }

            jurusanMahasiswa = cbJurusan.getValue();

            checkBox = verifikasiData.isSelected();

            Mahasiswa mahasiswa = new Mahasiswa(nimMahasiswa, angkatanMahasiswa, namaMahasiswa, kelasMahasiswa,
                    genderMahasiswa, jurusanMahasiswa);

            if (checkBox && !jurusanMahasiswa.equals("Pilih jurusan") && genderMahasiswa != null) {
                for (Mahasiswa i : dataMahasiswa) {
                    if (i.getNIM() == nimMahasiswa) {
                        dataMahasiswa.set(index, mahasiswa);
                        clearData();
                        break;
                    }
                    index++;
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Apakah anda yakin data tersebut benar? silahkan cek kembali");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Kesalahan dalam input data");
            alert.setContentText("Silahkan cek data yang anda masukan");
            alert.showAndWait();
        }
    }

    @FXML
    void clearTextField(ActionEvent event) {
        clearData();
    }

    @FXML
    void showData(MouseEvent event) {

        // Gunakan try and catch
        try {
            // Jika kolom terklik sebanyak 1 kali (gunakan 2 jika ingin double click)
            if (event.getClickCount() == 1) {

                // Text Field akan mengambil data dari tabel berdasarkan datanya masing - masing
                tfNim.setText(String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getNIM()));
                // Text Field NIM akan dibuat disable agar NIM tidak dapat diubah karena kita
                // akan menggunakan NIM sebagai ID
                tfNim.setDisable(true);
                tfAngkatan.setText(
                        String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getAngkatan()));
                tfNama.setText(String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getNama()));
                tfKelas.setText(String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getKelas()));
                cbJurusan.setValue(
                        String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getJurusan()));

                genderMahasiswa = String.valueOf(tableDataMahasiswa.getSelectionModel().getSelectedItem().getGender());

                if (genderMahasiswa.equals("Laki - laki")) {
                    lakilaki.setSelected(true);
                    perempuan.setSelected(false);
                }

                if (genderMahasiswa.equals("Perempuan")) {
                    perempuan.setSelected(true);
                    lakilaki.setSelected(false);
                }

            }

            // Catch disini berguna jika kolom yang diklik tidak memiliki data apapun
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Tidak ada data");
            alert.setContentText("Silahkan inputkan data mahasiswa terlebih dahulu");
            alert.showAndWait();
        }
    }

    public void initialize() {
        cbJurusan.setValue("Pilih jurusan");
        cbJurusan.setItems(dataJurusan);

        NIM.setCellValueFactory(new PropertyValueFactory<Mahasiswa, Integer>("NIM"));
        nama.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nama"));
        kelas.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("kelas"));
        angkatan.setCellValueFactory(new PropertyValueFactory<Mahasiswa, Integer>("angkatan"));
        jurusan.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("jurusan"));
        gender.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("gender"));

        tableDataMahasiswa.setItems(dataMahasiswa);

    }

    public void clearData() {
        tfNim.clear();
        tfNim.setDisable(false);
        tfNama.clear();
        tfAngkatan.clear();
        tfKelas.clear();
        lakilaki.setSelected(false);
        perempuan.setSelected(false);
        verifikasiData.setSelected(false);
        cbJurusan.setValue("Pilih jurusan");
    }

}