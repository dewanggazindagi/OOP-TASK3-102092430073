public class Mahasiswa {

    int NIM, angkatan;
    String nama, kelas, gender, jurusan;

    public Mahasiswa(int NIM, int angkatan, String nama, String kelas, String gender, String jurusan) {
        this.NIM = NIM;
        this.angkatan = angkatan;
        this.nama = nama;
        this.kelas = kelas;
        this.gender = gender;
        this.jurusan = jurusan;
    }

    public int getNIM() {
        return NIM;
    }

    public void setNIM(int nIM) {
        NIM = nIM;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(int angkatan) {
        this.angkatan = angkatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

}