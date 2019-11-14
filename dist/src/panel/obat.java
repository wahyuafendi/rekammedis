/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.placeholder.PlaceHolder;
import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */

 

public class obat extends javax.swing.JPanel {
private Statement stat;
private Connection Con;
private Connection con;
//private Connection conn = new koneksi().connect();
private ResultSet res;
private String t;
private Component rootPane;
int baris;
private PreparedStatement pst;
String[] dataPopup = new String[7];

    

    public obat(){
        initComponents();
        koneksi();
        tabel();
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
       kd_obat.setText(""); 
       nama_obat.setText("");
       jenis.setText("");
       keterangan.setText("");
       harga.setText("");
        }
       
       private void tabel(){ 
    DefaultTableModel t= new DefaultTableModel();
     t.addColumn("kd_obat"); 
     t.addColumn("nama_obat"); 
     t.addColumn("jenis"); 
     t.addColumn("keterangan"); 
     t.addColumn("harga");  
     tabel_obat.setModel(t); try{ res=stat.executeQuery("select * from obat"); 
     while (res.next()) { 
     t.addRow(new Object[]{ res.getString("kd_obat"),
     res.getString("nama_obat"), 
     res.getString("jenis"), 
     res.getString("keterangan"), 
     res.getString("harga"), 
     }); 
     }
     }catch (Exception e) { 
     JOptionPane.showMessageDialog(rootPane, e); 
     } 
    }
    public void setDataPopup(String[] datapopup){
        this.dataPopup = datapopup;

    }
    public String[] getDataPopup(){
        return dataPopup;
    } 
    
      private void buatkode(){
                  try {
    
String sql = "SELECT * FROM obat ORDER BY kd_obat DESC";


res=stat.executeQuery(sql);
if (res.next()) {
String kd_barang = res.getString("kd_obat").substring(2);
String AN = "" +(Integer.parseInt(kd_barang) + 1);
String Nol = "";
if(AN.length()==1)
{Nol = "00";}
else if(AN.length()==2)
{Nol = "0";}
else if(AN.length()==3)
{Nol = "";}
kd_obat.setText("O-"+ Nol +AN);
} else {
kd_obat.setText("O-001");
}
} catch (Exception e) {
e.printStackTrace();
    
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
        jLabel1 = new javax.swing.JLabel();
        kd_obat = new javax.swing.JTextField();
        nama_obat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jenis = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        keterangan = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        btedit = new javax.swing.JButton();
        bthapus = new javax.swing.JToggleButton();
        btsimpan = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(230, 230, 230));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Belwe Bd BT", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("OBAT");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kode Obat");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        kd_obat.setEditable(false);
        add(kd_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 275, -1));
        add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 275, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Obat");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));
        add(jenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 275, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Keterangan");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        keterangan.setColumns(20);
        keterangan.setRows(5);
        jScrollPane1.setViewportView(keterangan);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 275, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Harga");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));
        add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 275, -1));

        tabel_obat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabel_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode barang", "Nama Barang", "Stok", "Berat", "Title 5", "Title 6", "Title 7", "null"
            }
        ));
        tabel_obat.setGridColor(new java.awt.Color(153, 153, 153));
        tabel_obat.setSelectionBackground(new java.awt.Color(0, 153, 51));
        tabel_obat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_obatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_obat);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 559, 210));

        btedit.setText("UBAH");
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });
        add(btedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, -1, -1));

        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, -1, -1));

        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/panel.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed
         try {
            stat.executeUpdate("update obat set "
                + "kd_obat='"+kd_obat.getText()+"',"
                + "nama_obat='"+nama_obat.getText()+"',"
                + "jenis='"+jenis.getText()+"',"
                + "keterangan='"+keterangan.getText()+"',"
                + "harga='"+harga.getText()+"',"
                + " where " + "kd_obat='"+kd_obat.getText()+"'" );
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
        stat.executeUpdate("delete from obat where " 
        + "kd_obat='"+kd_obat.getText()
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
 stat.executeUpdate("insert into obat values (" 
+ "'" + kd_obat.getText()+"',"
+ "'" + nama_obat.getText()+"',"
+ "'" + jenis.getText()+"'," 
+ "'" + keterangan.getText()+ "',"
+ "'" + harga.getText()+ "')");
kosongkan(); 
JOptionPane.showMessageDialog(null, "Berhasil Menyimpan Data"); 
} catch (SQLException | HeadlessException e) { 
JOptionPane.showMessageDialog(null, "Perintah Salah : "+e);
}finally{
         tabel();  
            buatkode();
 } 
    }//GEN-LAST:event_btsimpanActionPerformed

    private void tabel_obatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_obatMouseClicked
        baris = tabel_obat.getSelectedRow();
        kd_obat.setText(tabel_obat.getValueAt(baris,0).toString());
        nama_obat.setText(tabel_obat.getValueAt(baris,1).toString());
        jenis.setText(tabel_obat.getValueAt(baris,2).toString());
        keterangan.setText(tabel_obat.getValueAt(baris,3).toString());
        harga.setText(tabel_obat.getValueAt(baris,4).toString());
      
    }//GEN-LAST:event_tabel_obatMouseClicked

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btedit;
    private javax.swing.JToggleButton bthapus;
    private javax.swing.JToggleButton btsimpan;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jenis;
    private javax.swing.JTextField kd_obat;
    private javax.swing.JTextArea keterangan;
    private javax.swing.JTextField nama_obat;
    private javax.swing.JTable tabel_obat;
    // End of variables declaration//GEN-END:variables
}
   

