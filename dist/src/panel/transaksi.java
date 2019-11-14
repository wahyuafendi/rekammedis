/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import panel.obat;
/**
 *
 * @author User
 */

 

public class transaksi extends javax.swing.JPanel {
//private Statement stat;
//private Connection Con;
private Connection con;
private ResultSet res;
private String t;
private java.sql.Statement st;
private Component rootPane;
private com.mysql.jdbc.Statement stat;
private com.mysql.jdbc.Connection Con;
int baris;
private String sql="";
private String  kodeobat;
private ResultSet obat;
//private ResultSet res;
private PreparedStatement pst;
private int hargaobat, jumlahobat,  jumlahitem,biayadokter, jumlahtotal,total_semua, bayar, kembali;
 //   private String  kodeobat;

    public transaksi(){
        initComponents();
          Locale locale = new Locale ("id","ID");
        locale.setDefault(locale);
         tgl.setText(tanggal);
        setJam();
        koneksi();
        tabel();
      combo_dokter();
      DaftarObat();
       buatkode();
       tabel2();
    //  combo_pasien();
    //  prosestambah();
     // total();
    }
    
     private void koneksi(){
    try {
    Class.forName("com.mysql.jdbc.Driver");
    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skripsiku", "root", "");
    stat=(Statement) con.createStatement();
    } catch (ClassNotFoundException | SQLException e) {
    JOptionPane.showMessageDialog(null, e);
    }
    }
     
       private void kosongkan()
       { 
          
       no_trans.setText("");
        kd_pasien.setText("");
       nama_pasien.setText("");
       tgl_fktur.setText("");
       jam_fktur.setText("");
       cmb_dokter.setSelectedItem("");
       cmb_obat.setSelectedItem("");
       kode_obat.setText("");
       harga.setText("");
       Txt_jumlahobat.setText("");
       Txt_totalharga.setText("");
       Txt_jumlahitem.setText("");
         biaya_dokter.setText("");
       Txt_jumlahtotal.setText("");
       totalsemua.setText("");
      Txt_jumlahbayar.setText("");
      Txt_kembali.setText("");
       }
        java.util.Date tglsekarang = new java.util.Date();
private final SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
    private final String tanggal = smpdtfmt.format(tglsekarang);
    private Statement stm;
   // private Connection con;

       
       private void tabel(){ 
  DefaultTableModel t= new DefaultTableModel();
  
     t.addColumn("no_trans"); 
     t.addColumn("kd_pasien");
     t.addColumn("nama_pasien"); 
     t.addColumn("Tanggal");
     t.addColumn("Jam");  
     t.addColumn("nama_dokter");
     t.addColumn("kode obat");
     t.addColumn("nama obat");  
     t.addColumn("harga"); 
     t.addColumn("jumlahobat");
     t.addColumn("totalharga");
     t.addColumn("jumlahitem"); 
       t.addColumn("biayadokter"); 
     t.addColumn("jumlahtotal"); 
     t.addColumn("jumlahbayar");
     t.addColumn("kembali");
     tabel_diagnosa.setModel(t); try{ res=stat.executeQuery("select * from transaksi"); 
     while (res.next()) { 
     t.addRow(new Object[]{ 
        res.getString("no_trans"),
        res.getString("kd_pasien"), 
        res.getString("nama_pasien"), 
        res.getString("tgl_fktur"), 
        res.getString("jam_faktur"), 
        res.getString("nama_dokter"),
        res.getString("kd_obat"),
        res.getString("nama_obat"),
        res.getString("harga"),
        res.getString("jumlahobat"),
        res.getString("totalharga"),
        res.getString("jumlahitem"),
        res.getString("biaya_dokter"),
        res.getString("jumlahtotal"),
        res.getString("totalsemua"),
        res.getString("jumlahbayar"),
        res.getString("kembali")
     }); 
     }
     }catch (Exception e) { 
     JOptionPane.showMessageDialog(rootPane, e); 
     } 
    }
        private void tabel2(){ 
  DefaultTableModel t= new DefaultTableModel();
  
     t.addColumn("kode_pasien");
     t.addColumn("nama_pasien"); 
     
     tabel_pasien.setModel(t); try{ res=stat.executeQuery("select * from pasien"); 
     while (res.next()) { 
     t.addRow(new Object[]{ res.getString("kd_pasien"),
     res.getString("nama_pasien"), 
    
     }); 
     }
     }catch (Exception e) { 
     JOptionPane.showMessageDialog(rootPane, e); 
     } 
    }
        void cetak_satuan(){
            try{
            String namaFile = "src/laporan/struk.jasper";
            java.sql.Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/skripsiku", "root", "");
            HashMap parameter = new HashMap();
            
            parameter.put("kode",txtcari.getText());
            File report_file = new File(namaFile);
            net.sf.jasperreports.engine.JasperReport jasperreport = (net.sf.jasperreports.engine.JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, parameter, conn);
            JasperViewer.viewReport(jasperprint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        }catch (Exception e){
        JOptionPane.showMessageDialog(null,e.getMessage());
        }
    

    }
private void buatkode(){
try {
    
String sql = "SELECT * FROM transaksi ORDER BY no_trans DESC";


res=stat.executeQuery(sql);
if (res.next()) {
String kd_barang = res.getString("no_trans").substring(2);
String AN = "" +(Integer.parseInt(kd_barang) + 1);
String Nol = "";
if(AN.length()==1)
{Nol = "00";}
else if(AN.length()==2)
{Nol = "0";}
else if(AN.length()==3)
{Nol = "";}
no_trans.setText("T-"+ Nol +AN);
} else {
no_trans.setText("T-001");
}
} catch (Exception e) {
e.printStackTrace();
    
}
   }             
 
  
       
      public final void setJam(){
ActionListener taskPerformer = new ActionListener() {

            @Override
public void actionPerformed(ActionEvent evt) {
String nol_jam = "", nol_menit = "",nol_detik = "";

java.util.Date dateTime = new java.util.Date();
int nilai_jam = dateTime.getHours();
int nilai_menit = dateTime.getMinutes();
int nilai_detik = dateTime.getSeconds();

if(nilai_jam <= 9) nol_jam= "0";
if(nilai_menit <= 9) nol_menit= "0";
if(nilai_detik <= 9) nol_detik= "0";

String jam = nol_jam + Integer.toString(nilai_jam);
String menit = nol_menit + Integer.toString(nilai_menit);
String detik = nol_detik + Integer.toString(nilai_detik);

lblwktu.setText(jam+":"+menit+":"+detik+"");
}
};
new Timer(1000, taskPerformer).start();
}
   
private void DaftarObat(){
        cmb_obat.removeAllItems();
        cmb_obat.addItem("Pilih");
        try {
            String sql ="Select * FROM obat";
            java.sql.Statement st=con.createStatement();
            obat=st.executeQuery(sql);
            while(obat.next()){
                String Alliasob=obat.getString("nama_obat");
                cmb_obat.addItem(Alliasob);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Kode Obat \n"
                +e.getMessage());
        }}
  
  
    private void prosestambah(){
    try {
        DefaultTableModel tableModel = (DefaultTableModel)Gridobat.getModel();
        String[]data = new String[6];
        data[0] = String.valueOf(cmb_obat.getSelectedItem());
        data[1] = kode_obat.getText();
        data[2] = harga.getText();
        data[3] = Txt_jumlahobat.getText();
        data[4] = Txt_totalharga.getText();
        tableModel.addRow(data);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Memasukkan Data \n" +e.getMessage());
    }
}
        private void total(){
        int jumlahBaris = Gridobat.getRowCount();
        int jumlahtotal =0, jumlahitem=0;
        int jumlahjual, totalharga;
        
        TableModel tblmodel;
        tblmodel = Gridobat.getModel();
        for (int i=0; i<jumlahBaris; i++){
            jumlahjual = Integer.parseInt(tblmodel.getValueAt(i, 3).toString());
            jumlahitem=jumlahitem+jumlahjual;
            totalharga = Integer.parseInt(tblmodel.getValueAt(i, 4).toString());
            jumlahtotal=jumlahtotal+totalharga;}
           
        Txt_jumlahtotal.setText(String.valueOf(jumlahtotal));
        Txt_jumlahitem.setText(String.valueOf(jumlahitem));
        
    }

public final void combo_dokter()
    {
        try {
//        Connection c=ClassKoneksi.getkoneksi();
         stat = (com.mysql.jdbc.Statement) con.createStatement();
        String sql_tc = "select nama_dokter from dokter order by kd_dokter asc";
        res = stat.executeQuery(sql_tc);

        while(res.next()){
            String nama = res.getString("nama_dokter");
            cmb_dokter.addItem(nama);
        }
        //res.close(); stat.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
   /* public final void combo_pasien()
    {
        try {
//        Connection c=ClassKoneksi.getkoneksi();
         stat = (com.mysql.jdbc.Statement) con.createStatement();
        String sql_tc = "select nama_pasien from pasien order by kd_pasien asc";
        res = stat.executeQuery(sql_tc);

        while(res.next()){
            String nama = res.getString("nama_pasien");
            nama_paise_pasien.addItem(nama);
        }
        //res.close(); stat.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        no_trans = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_diagnosa = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        bthapus = new javax.swing.JToggleButton();
        btsimpan = new javax.swing.JToggleButton();
        jLabel8 = new javax.swing.JLabel();
        cmb_dokter = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tgl_fktur = new javax.swing.JTextField();
        jam_fktur = new javax.swing.JTextField();
        btwaktu = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblwktu = new javax.swing.JLabel();
        cmb_obat = new javax.swing.JComboBox<>();
        kode_obat = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Txt_jumlahobat = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        Txt_totalharga = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Gridobat = new javax.swing.JTable();
        Btn_Tambah = new javax.swing.JButton();
        Btn_Kurang = new javax.swing.JButton();
        Txt_jumlahitem = new javax.swing.JTextField();
        Txt_jumlahtotal = new javax.swing.JTextField();
        Txt_jumlahbayar = new javax.swing.JTextField();
        Txt_kembali = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        txtcari2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        kd_pasien = new javax.swing.JTextField();
        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        biaya_dokter = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        totalsemua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Belwe Bd BT", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TRANSAKSI");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 230, -1));

        no_trans.setEditable(false);
        add(no_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 110, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("No Transakasi");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama pasien");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        tabel_diagnosa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabel_diagnosa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Pelanggan", "Nama Pelanggan", "Kontak", "Email", "Alamat"
            }
        ));
        tabel_diagnosa.setGridColor(new java.awt.Color(153, 153, 153));
        tabel_diagnosa.setSelectionBackground(new java.awt.Color(0, 153, 51));
        tabel_diagnosa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_diagnosaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_diagnosa);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 1030, 90));

        btedit.setBackground(new java.awt.Color(255, 255, 255));
        btedit.setText("UBAH");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        add(btedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 110, 30));

        bthapus.setBackground(new java.awt.Color(255, 255, 255));
        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 110, 30));

        btsimpan.setBackground(new java.awt.Color(255, 255, 255));
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 110, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Dokter");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        add(cmb_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 120, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tanggal Transaksi");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jam ");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));
        add(tgl_fktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 110, -1));
        add(jam_fktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 110, -1));

        btwaktu.setText("waktu");
        btwaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btwaktuActionPerformed(evt);
            }
        });
        add(btwaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 193, -1, -1));

        jLabel11.setFont(new java.awt.Font("Bell Gothic Std Light", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tanggal ");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 24));

        tgl.setFont(new java.awt.Font("Open 24 Display St", 0, 18)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setText("tgl");
        add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 94, -1));

        jLabel12.setFont(new java.awt.Font("Bell Gothic Std Light", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jam ");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, 24));

        lblwktu.setFont(new java.awt.Font("Open 24 Display St", 0, 18)); // NOI18N
        lblwktu.setForeground(new java.awt.Color(255, 255, 255));
        lblwktu.setText("lblwktu");
        add(lblwktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 100, -1));

        cmb_obat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmb_obat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_obatItemStateChanged(evt);
            }
        });
        add(cmb_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 120, -1));
        add(kode_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 120, -1));
        add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 120, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nama Obat");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Kode obat");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Harga Obat");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Jumlah Obat ");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 100, 20));

        Txt_jumlahobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_jumlahobatActionPerformed(evt);
            }
        });
        add(Txt_jumlahobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 120, -1));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Total Harga Obat");
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        Txt_totalharga.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Txt_totalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_totalhargaActionPerformed(evt);
            }
        });
        add(Txt_totalharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 120, -1));

        Gridobat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Harga Obat", "Jumlah Obat", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Gridobat);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 460, 140));

        Btn_Tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/add-16x16.png"))); // NOI18N
        Btn_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TambahActionPerformed(evt);
            }
        });
        add(Btn_Tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 30, -1));

        Btn_Kurang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikon3/delete-16x16.png"))); // NOI18N
        Btn_Kurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_KurangActionPerformed(evt);
            }
        });
        add(Btn_Kurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 30, -1));

        Txt_jumlahitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_jumlahitemActionPerformed(evt);
            }
        });
        add(Txt_jumlahitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 80, -1));

        Txt_jumlahtotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        add(Txt_jumlahtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 420, 80, -1));

        Txt_jumlahbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Txt_jumlahbayarActionPerformed(evt);
            }
        });
        Txt_jumlahbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Txt_jumlahbayarKeyReleased(evt);
            }
        });
        add(Txt_jumlahbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, 80, -1));
        add(Txt_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 80, -1));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Kembali");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, -1, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Jumlah Bayar");
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 420, -1, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Jumlah Item Obat");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Jumlah Total Biaya");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, -1, -1));
        add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 110, -1));

        tabel_pasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_pasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pasienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabel_pasien);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 460, 90));
        add(txtcari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 100, -1));

        jButton3.setText("cari");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, -1));
        add(kd_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 110, -1));
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 120, -1));

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, -1, 20));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cetak Struk");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kode Pasien");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        biaya_dokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                biaya_dokterActionPerformed(evt);
            }
        });
        add(biaya_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 80, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Biaya dokter");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, -1, -1));

        totalsemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalsemuaActionPerformed(evt);
            }
        });
        add(totalsemua, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, 80, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Semua");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 460, 100));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panel.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nama pasien");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
 
    /*    try {
            stat.executeUpdate("update transaksi set "
             
                + "no_trans='"+no_trans.getText()+"',"
                + "nama_pasien='"+cmb_pasien.getSelectedItem()+"',"
                + "tgl_fktur='"+tgl_fktur.getText()+"',"
                + "jam_faktur='"+jam_fktur.getText()+"',"
              //  + "kd_dokter='"+kd_dokter.getText()+"',"
                + "nama_dokter='"+cmb_dokter.getSelectedItem()+"',"
              + "kd_obat='"+cmb_obat.getSelectedItem()+"',"
                    + "jam_faktur='"+nama_obat.getText()+"',"
                    + "harga='"+harga.getText()+"',"
                + " where " + "transaksi='"+no_trans.getText()+"'" );
            kosongkan();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil Di update");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }finally{
         tabel();
             buatkode();
        }    */
    }//GEN-LAST:event_bteditActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        try { 
        stat.executeUpdate("delete from transaksi where " 
        + "no_trans='"+no_trans.getText()
        +"'" ); 
        JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException | HeadlessException e) { 
        JOptionPane.showMessageDialog(null, "pesan salah : "+e);
         } finally{
         tabel();
             buatkode();
              tabel2();
          }
    }//GEN-LAST:event_bthapusActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
 

        try {
 stat.executeUpdate("insert into transaksi values (" 
 
+ "'" + no_trans.getText()+"',"
+ "'" + kd_pasien.getText()+"',"
+ "'" + nama_pasien.getText()+"',"
+ "'" + tgl_fktur.getText()+"'," 
+ "'" + jam_fktur.getText()+ "',"
+ "'" + cmb_dokter.getSelectedItem()+"',"
+ "'" + cmb_obat.getSelectedItem()+ "',"
+ "'" + kode_obat.getText()+ "',"
+ "'" + harga.getText()+ "',"
+ "'" + Txt_jumlahobat.getText()+ "',"
+ "'" + Txt_totalharga.getText()+ "',"
+ "'" + Txt_jumlahitem.getText()+ "',"
+ "'" + biaya_dokter.getText()+ "',"        
+ "'" + Txt_jumlahtotal.getText()+ "',"
+ "'" + totalsemua.getText()+ "',"
+ "'" + Txt_jumlahbayar.getText()+ "',"
+ "'" + Txt_kembali.getText()+ "')");
 
 
 
 
kosongkan(); 
JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data"); 
} catch (SQLException | HeadlessException e) { 
JOptionPane.showMessageDialog(null, "Perintah Salah : "+e);
}finally{
         tabel();     
          DaftarObat();
              buatkode();
    //  combo_pasien();
     // prosestambah();
     // total();
 } 
    }//GEN-LAST:event_btsimpanActionPerformed

    private void tabel_diagnosaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_diagnosaMouseClicked

   //     baris = tabel_diagnosa.getSelectedRow();
      
     //   jdate.SetDate(tabel_diagnosa.getValueAt(baris,0).toString());
     //   no_trans.setText(tabel_diagnosa.getValueAt(baris,0).toString());
      //  kd_pasien.setText(tabel_diagnosa.getValueAt(baris,1).toString());
      //  nama_pasien.setText(tabel_diagnosa.getValueAt(baris,1).toString());
      //   tgl_fktur.setText(tabel_diagnosa.getValueAt(baris,2).toString());
      //    jam_fktur.setText(tabel_diagnosa.getValueAt(baris,3).toString());
      //  cmb_dokter.setSelectedItem(tabel_diagnosa.getValueAt(baris,4).toString());
      //    cmb_obat.setSelectedItem(tabel_diagnosa.getValueAt(baris,5).toString());
     //   nama_obat.setText(tabel_diagnosa.getValueAt(baris,6).toString());
       //    harga.setText(tabel_diagnosa.getValueAt(baris,7).toString());
        
    }//GEN-LAST:event_tabel_diagnosaMouseClicked

    private void btwaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btwaktuActionPerformed
  tgl_fktur.setText(tgl.getText());
        jam_fktur.setText(lblwktu.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_btwaktuActionPerformed

    private void cmb_obatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_obatItemStateChanged
        // TODO add your handling code here:
        try {
            sql="Select * FROM obat WHERE "
            + "nama_obat='" + cmb_obat.getSelectedItem() +"'";
            java.sql.Statement st = con.createStatement();
            ResultSet obat = st.executeQuery(sql);
            while(obat.next()){
                kode_obat.setText(obat.getString("kd_obat"));
                harga.setText(obat.getString("harga"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Detail Pelanggan \n"
                +e.getMessage());
        }
    }//GEN-LAST:event_cmb_obatItemStateChanged

    private void Txt_jumlahobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_jumlahobatActionPerformed
        // TODO add your handling code here:
        hargaobat=Integer.parseInt(harga.getText());
        jumlahobat=Integer.parseInt(Txt_jumlahobat.getText());
        jumlahtotal=hargaobat*jumlahobat;
        Txt_totalharga.setText(String.valueOf(jumlahtotal));
    }//GEN-LAST:event_Txt_jumlahobatActionPerformed

    private void Txt_totalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_totalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_totalhargaActionPerformed

    private void Btn_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TambahActionPerformed
        // TODO add your handling code here:
        prosestambah();
        total();
       // kosongkan();
    }//GEN-LAST:event_Btn_TambahActionPerformed

    private void Btn_KurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_KurangActionPerformed
        // /TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Gridobat.getModel();
        int row = Gridobat.getSelectedRow();
        if(row>=0){
            int oke=JOptionPane.showConfirmDialog(null,
                "Yakin Mau Hapus?","Konfirmasi",
                JOptionPane.YES_NO_OPTION);
            if(oke==0){
                model.removeRow(row);
            }
        }
    }//GEN-LAST:event_Btn_KurangActionPerformed

    private void Txt_jumlahbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_jumlahbayarActionPerformed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_Txt_jumlahbayarActionPerformed

    private void Txt_jumlahitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Txt_jumlahitemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_jumlahitemActionPerformed

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked

        baris = tabel_pasien.getSelectedRow();

        //   jdate.SetDate(tabel_diagnosa.getValueAt(baris,0).toString());
        kd_pasien.setText(tabel_pasien.getValueAt(baris,0).toString());
        nama_pasien.setText(tabel_pasien.getValueAt(baris,1).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel tabelTampil1 = new DefaultTableModel();
        tabelTampil1.addColumn("kd_pasien");
        tabelTampil1.addColumn("nama_pasien ");

        try{
            koneksi();
            String sql = "Select * from pasien where kd_pasien like '%" + txtcari2.getText() + "%'" +
            "or nama_pasien like '%" + txtcari2.getText() + "%'";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                tabelTampil1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2)
                });
            }
            tabel_pasien.setModel(tabelTampil1);

        }catch (Exception e){

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void totalsemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalsemuaActionPerformed
// total_semua=total_semua+biayadokter;
      //    totalsemua.setText(String.valueOf(totalsemua));        // TODO add your handling code here:
    }//GEN-LAST:event_totalsemuaActionPerformed

    private void biaya_dokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_biaya_dokterActionPerformed
biayadokter=Integer.parseInt(biaya_dokter.getText());
        jumlahtotal=Integer.parseInt(Txt_jumlahtotal.getText());
        total_semua=jumlahtotal+biayadokter;
        totalsemua.setText(String.valueOf(total_semua));

        
        
        
        
    //    hargaobat=Integer.parseInt(harga.getText());
      //  jumlahobat=Integer.parseInt(Txt_jumlahobat.getText());
        //jumlahtotal=hargaobat*jumlahobat;
       // Txt_totalharga.setText(String.valueOf(jumlahtotal));
        
      //  total_semua=total_semua+biayadokter;
        //  totalsemua.setText(String.valueOf(totalsemua));        // TODO add your handling code here:
    }//GEN-LAST:event_biaya_dokterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
cetak_satuan();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Txt_jumlahbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Txt_jumlahbayarKeyReleased
      
     jumlahtotal=Integer.parseInt(Txt_jumlahtotal.getText());
        bayar=Integer.parseInt(Txt_jumlahbayar.getText());
        kembali=bayar-total_semua;
        Txt_kembali.setText(String.valueOf(kembali));        // TODO add your handling code here:
    }//GEN-LAST:event_Txt_jumlahbayarKeyReleased

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Kurang;
    private javax.swing.JButton Btn_Tambah;
    private javax.swing.JTable Gridobat;
    private javax.swing.JTextField Txt_jumlahbayar;
    private javax.swing.JTextField Txt_jumlahitem;
    private javax.swing.JTextField Txt_jumlahobat;
    private javax.swing.JTextField Txt_jumlahtotal;
    private javax.swing.JTextField Txt_kembali;
    private javax.swing.JTextField Txt_totalharga;
    private javax.swing.JTextField biaya_dokter;
    private javax.swing.JButton btedit;
    private javax.swing.JToggleButton bthapus;
    private javax.swing.JToggleButton btsimpan;
    private javax.swing.JButton btwaktu;
    private javax.swing.JComboBox cmb_dokter;
    private javax.swing.JComboBox<String> cmb_obat;
    private javax.swing.JTextField harga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jam_fktur;
    private javax.swing.JTextField kd_pasien;
    private javax.swing.JTextField kode_obat;
    private javax.swing.JLabel lblwktu;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTextField no_trans;
    private javax.swing.JTable tabel_diagnosa;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField tgl_fktur;
    private javax.swing.JTextField totalsemua;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari2;
    // End of variables declaration//GEN-END:variables
}
   

