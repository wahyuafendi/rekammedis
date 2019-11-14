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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author User
 */

 

public class Diagnosa extends javax.swing.JPanel {
private Statement stat;
private Connection Con;
private Connection con;
private ResultSet res;
private PreparedStatement pst;
private String t;
private Component rootPane;
int baris;
    

    public Diagnosa(){
        initComponents();
          Locale locale = new Locale ("id","ID");
        locale.setDefault(locale);
         tgl.setText(tanggal);
        setJam();
        koneksi();
        tabel();
        tabel2();
     //   DaftarObat();
//        carirekammedis(); 
      combo_dokter();
      buatkode();
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
          
       kd_pasien.setText("");
       nama_pasien.setText("");
       tgl_fktur.setText("");
jam_fktur.setText("");
       cmb_dokter.setSelectedItem("");
        no_medis.setText(""); 
       keluhan.setText("");
       hasil.setText("");
               }
        java.util.Date tglsekarang = new java.util.Date();
    private SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
    private String tanggal = smpdtfmt.format(tglsekarang);
    private Statement stm;
   // private Connection con;

       
       private void tabel(){ 
  DefaultTableModel t= new DefaultTableModel();
  
     t.addColumn("kd_pasien"); 
     t.addColumn("nama_pasien"); 
      t.addColumn("Tanggal");
 t.addColumn("Jam");
   // t.addColumn("kd_dokter");
     t.addColumn("nama_dokter");
        t.addColumn("rekammedis");
     t.addColumn("keluhan"); 
     t.addColumn("hasil"); 
     tabel_diagnosa.setModel(t); try{ res=stat.executeQuery("select * from diagnosa order by kd_pasien asc"); 
     while (res.next()) { 
     t.addRow(new Object[]{ res.getString("kd_pasien"),
     res.getString("nama_pasien"), 
        res.getString("tanggal_faktur"), 
 res.getString("jam_faktur"), 
   //  res.getString("kd_dokter"),
     res.getString("nama_dokter"),
       res.getString("rekammedis"),
     res.getString("keluhan"), 
     res.getString("hasil")
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

      
      
  void cetak_satuan(){
            try{
            String namaFile = "src/laporan/diagnosa2.jasper";
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
    
String sql = "SELECT * FROM diagnosa ORDER BY rekammedis DESC";


res=stat.executeQuery(sql);
if (res.next()) {
String kd_barang = res.getString("rekammedis").substring(2);
String AN = "" +(Integer.parseInt(kd_barang) + 1);
String Nol = "";
if(AN.length()==1)
{Nol = "00";}
else if(AN.length()==2)
{Nol = "0";}
else if(AN.length()==3)
{Nol = "";}
no_medis.setText("R-"+ Nol +AN);
} else {
no_medis.setText("R-001");
}
} catch (Exception e) {
e.printStackTrace();
    
}
   }             
 
public void combo_dokter()
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hasil = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_diagnosa = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        bthapus = new javax.swing.JToggleButton();
        btsimpan = new javax.swing.JToggleButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        keluhan = new javax.swing.JTextArea();
        cari2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        no_medis = new javax.swing.JTextField();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_pasien = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        txtcari2 = new javax.swing.JTextField();
        kd_pasien = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Belwe Bd BT", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DIAGNOSA");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Pasien");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama pasien");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));
        add(nama_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 170, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Diagnosa");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        hasil.setColumns(20);
        hasil.setRows(5);
        jScrollPane1.setViewportView(hasil);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 275, 80));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 520, 210));

        btedit.setBackground(new java.awt.Color(255, 255, 255));
        btedit.setText("UBAH");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        add(btedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 90, 30));

        bthapus.setBackground(new java.awt.Color(255, 255, 255));
        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 90, 30));

        btsimpan.setBackground(new java.awt.Color(255, 255, 255));
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 90, 30));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Dokter");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Keluhan");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        keluhan.setColumns(20);
        keluhan.setRows(5);
        jScrollPane3.setViewportView(keluhan);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 270, 70));

        cari2.setText("Rekam Medis");
        cari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari2ActionPerformed(evt);
            }
        });
        add(cari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, 20));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rekam Medis");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        no_medis.setEditable(false);
        add(no_medis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 200, -1));

        add(cmb_dokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 120, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tanggal kunjungan");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Jam kunjungan");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));
        add(tgl_fktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 100, -1));
        add(jam_fktur, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 100, -1));

        btwaktu.setText("waktu");
        btwaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btwaktuActionPerformed(evt);
            }
        });
        add(btwaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Bell Gothic Std Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tanggal =");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 24));

        tgl.setFont(new java.awt.Font("Open 24 Display St", 0, 18)); // NOI18N
        tgl.setForeground(new java.awt.Color(255, 255, 255));
        tgl.setText("tgl");
        add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 94, -1));

        jLabel12.setFont(new java.awt.Font("Bell Gothic Std Light", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Jam =");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 60, 24));

        lblwktu.setFont(new java.awt.Font("Open 24 Display St", 0, 18)); // NOI18N
        lblwktu.setForeground(new java.awt.Color(255, 255, 255));
        lblwktu.setText("lblwktu");
        add(lblwktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 90, -1));

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

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, 90));

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 170, 30));

        txtcari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcari2ActionPerformed(evt);
            }
        });
        txtcari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcari2KeyReleased(evt);
            }
        });
        add(txtcari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 100, -1));
        add(kd_pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 170, -1));

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(783, 310, 70, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panel.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 1050, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
 
        try {
            stat.executeUpdate("update diagnosa set "
             
                + "kd_pasien='"+kd_pasien.getText()+"',"
                + "nama_pasien='"+nama_pasien.getText()+"',"
                + "tanggal_faktur='"+tgl_fktur.getText()+"',"
                + "jam_faktur='"+jam_fktur.getText()+"',"
              //  + "kd_dokter='"+kd_dokter.getText()+"',"
                + "nama_dokter='"+cmb_dokter.getSelectedItem()+"',"
                + "rekammedis='"+no_medis.getText()+"',"
                + "keluhan='"+keluhan.getText()+"',"
                + "hasil='"+hasil.getText()+"'"
                + " where " + "rekammedis='"+no_medis.getText()+"'" );
            kosongkan();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil Di update");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }finally{
         tabel();
          buatkode();
        }    
    }//GEN-LAST:event_bteditActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        try { 
        stat.executeUpdate("delete from diagnosa where " 
        + "kd_pasien='"+kd_pasien.getText()
        +"'" ); 
        JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException | HeadlessException e) { 
        JOptionPane.showMessageDialog(null, "pesan salah : "+e);
         } finally{
         tabel();
          buatkode();
          }
    }//GEN-LAST:event_bthapusActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
 

        try {
 stat.executeUpdate("insert into diagnosa values (" 
 
+ "'" + kd_pasien.getText()+"',"
+ "'" + nama_pasien.getText()+"',"
+ "'" + tgl_fktur.getText()+"'," 
+ "'" + jam_fktur.getText()+ "',"
+ "'" + cmb_dokter.getSelectedItem()+"',"
+ "'" + no_medis.getText()+"',"  
+ "'" + keluhan.getText()+"'," 
+ "'" + hasil.getText()+ "')");
kosongkan(); 
JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data"); 
} catch (SQLException | HeadlessException e) { 
JOptionPane.showMessageDialog(null, "Perintah Salah : "+e);
}finally{
         tabel();    
         kosongkan();
          buatkode();
 } 
    }//GEN-LAST:event_btsimpanActionPerformed

    private void tabel_diagnosaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_diagnosaMouseClicked

        baris = tabel_diagnosa.getSelectedRow();
      
     //   jdate.SetDate(tabel_diagnosa.getValueAt(baris,0).toString());
        kd_pasien.setText(tabel_diagnosa.getValueAt(baris,0).toString());
        nama_pasien.setText(tabel_diagnosa.getValueAt(baris,1).toString());
         tgl_fktur.setText(tabel_diagnosa.getValueAt(baris,2).toString());
          jam_fktur.setText(tabel_diagnosa.getValueAt(baris,3).toString());
  //      kd_dokter.setText(tabel_diagnosa.getValueAt(baris,2).toString());
        cmb_dokter.setSelectedItem(tabel_diagnosa.getValueAt(baris,4).toString());
          no_medis.setText(tabel_diagnosa.getValueAt(baris,5).toString());
        keluhan.setText(tabel_diagnosa.getValueAt(baris,6).toString());
        hasil.setText(tabel_diagnosa.getValueAt(baris,7).toString());
    }//GEN-LAST:event_tabel_diagnosaMouseClicked

    private void cari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari2ActionPerformed
try { 
res=stat.executeQuery("select * from diagnosa where "+ "kd_pasien='" +kd_pasien.getText()
+"'" ); while (res.next()) 
{ 
    kd_pasien.setText(res.getString("kd_pasien")); 
nama_pasien.setText(res.getString("nama_pasien"));
tgl_fktur.setText(res.getString("tanggal_faktur"));
jam_fktur.setText(res.getString("jam_faktur"));
    //kd_dokter.setText(res.getString("kd_dokter")); 
cmb_dokter.setSelectedItem(res.getString("nama_dokter"));
    no_medis.setText(res.getString("rekammedis")); 
keluhan.setText(res.getString("keluhan"));
hasil.setText(res.getString("hasil"));

}                                        
 }       
catch (Exception e) { 
JOptionPane.showMessageDialog(rootPane, e); 
}         // TODO add your handling code here:
    }//GEN-LAST:event_cari2ActionPerformed

    private void btwaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btwaktuActionPerformed
  tgl_fktur.setText(tgl.getText());
        jam_fktur.setText(lblwktu.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_btwaktuActionPerformed

    private void tabel_pasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pasienMouseClicked

        baris = tabel_pasien.getSelectedRow();
      
     //   jdate.SetDate(tabel_diagnosa.getValueAt(baris,0).toString());
        kd_pasien.setText(tabel_pasien.getValueAt(baris,0).toString());
        nama_pasien.setText(tabel_pasien.getValueAt(baris,1).toString());
         
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_pasienMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
cetak_satuan();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void txtcari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcari2ActionPerformed
           // TODO add your handling code here:
    }//GEN-LAST:event_txtcari2ActionPerformed

    private void txtcari2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari2KeyReleased
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

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtcari2KeyReleased

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased

        DefaultTableModel tabelTampil1 = new DefaultTableModel();
        tabelTampil1.addColumn("kd_pasien");
        tabelTampil1.addColumn("nama_pasien ");
        tabelTampil1.addColumn("tanggal_faktur");
        tabelTampil1.addColumn("jam_faktur");
        tabelTampil1.addColumn("nama_dokter");
        tabelTampil1.addColumn("rekammedis");
        tabelTampil1.addColumn("keluhan");
        tabelTampil1.addColumn("hasil");
        try{
            koneksi();
            String sql = "Select * from diagnosa where kd_pasien like '%" + txtcari.getText() + "%'" +
            "or rekammedis like '%" + txtcari.getText() + "%'";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
            while (res.next()) {
                tabelTampil1.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8)
                });
            }
            tabel_diagnosa.setModel(tabelTampil1);

        }catch (Exception e){

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtcariKeyReleased

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedit;
    private javax.swing.JToggleButton bthapus;
    private javax.swing.JToggleButton btsimpan;
    private javax.swing.JButton btwaktu;
    private javax.swing.JButton cari2;
    private javax.swing.JComboBox cmb_dokter;
    private javax.swing.JTextArea hasil;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jam_fktur;
    private javax.swing.JTextField kd_pasien;
    private javax.swing.JTextArea keluhan;
    private javax.swing.JLabel lblwktu;
    private javax.swing.JTextField nama_pasien;
    private javax.swing.JTextField no_medis;
    private javax.swing.JTable tabel_diagnosa;
    private javax.swing.JTable tabel_pasien;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField tgl_fktur;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari2;
    // End of variables declaration//GEN-END:variables
}
   

