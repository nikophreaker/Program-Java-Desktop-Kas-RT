/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Comparator;
//import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
//import javax.swing.ListSelectionModel;
//import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
//import javax.swing.table.TableModel;
//import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nikx
 */
public class pnl_lkm extends javax.swing.JPanel {
    
private Connection conn = new koneksi().connect();
    DefaultTableModel tabmode;
    
//    private Random generator;
//    private TableRowSorter<DefaultTableModel>sorter;            
            
    /**
     * Creates new form pnl_home
     */
    
    public pnl_lkm() {
        initComponents();
        datatable();
        pemasukan();
        pengeluaran();
        totalkas();
    }

//    public boolean isCellEditable(int row, int col)
//        { return true; }
//    public void setValueAt(Object value, int row, int col) {
//        tabel_gk[row][col] = value;
//        fireTableCellUpdated(row, col);
//    }
    
//    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        TableModel model = (TableModel)e.getSource();
//        Object data = model.getValueAt(row, column);
//    }
    
    protected void datatable(){
        Object[] Baris ={"ID Kas Masuk","Tanggal Masuk","No KK","Nama Lengkap","Nama Transaksi","Keterangan","Jumlah Masuk"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_lkm.setModel(tabmode);
        String sql = "select * from kas_masuk inner join anggota on kas_masuk.kd_anggota = anggota.kd_anggota inner join transaksi on kas_masuk.id_transaksi = transaksi.id_transaksi ORDER BY tgl_km ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_km");
                String b = hasil.getString("tgl_km");
                String c = hasil.getString("kd_anggota");
                String d = hasil.getString("nm_anggota");
                String e = hasil.getString("nm_transaksi");
                String f = hasil.getString("ket_masuk");
                String g = hasil.getString("masuk");
            
                String[] data={a,b,c,d,e,f,g};
                tabmode.addRow(data);
            }
        } catch (Exception e){}
    }
    
    protected void cari(){
        java.util.Date utilStartDate = tgl1.getDate();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        java.util.Date utilStartDate2 = tgl2.getDate();
        java.sql.Date sqlStartDate2 = new java.sql.Date(utilStartDate2.getTime());
        jLabel10.setText(sqlStartDate.toString() +" s/d "+sqlStartDate2.toString());
        Object[] Baris ={"ID Kas Masuk","Tanggal Masuk","No KK","Nama Lengkap","Nama Transaksi","Keterangan","Jumlah Masuk"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_lkm.setModel(tabmode);
        try {
        String sql = "select * from kas_masuk inner join anggota on kas_masuk.kd_anggota = anggota.kd_anggota inner join transaksi on kas_masuk.id_transaksi = transaksi.id_transaksi"
                + " WHERE tgl_km BETWEEN '" + sqlStartDate.toString() + "' AND '" + sqlStartDate2.toString() + "'"
                + " ORDER BY tgl_km ASC";
            PreparedStatement pst = conn.prepareStatement(sql);
//            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = pst.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_km");
                String b = hasil.getString("tgl_km");
                String c = hasil.getString("kd_anggota");
                String d = hasil.getString("nm_anggota");
                String e = hasil.getString("nm_transaksi");
                String f = hasil.getString("ket_masuk");
                String g = hasil.getString("masuk");
            
                String[] data={a,b,c,d,e,f,g};
                tabmode.addRow(data);
            }
        } catch (Exception e){
        JOptionPane.showMessageDialog(null,e);}
    }
    
//    private void pemasukan(){
//        try{
//          String sql="select SUM(masuk) AS pemasukan from kas_masuk";
//          PreparedStatement pst = conn.prepareStatement(sql);
//          ResultSet rs=pst.executeQuery(sql);
//          
//          while(rs.next()){
//          String pemasukans =rs.getString("pemasukan");
//          if(pemasukans == null){pemasukans = "0";}
//          pemasukan.setText(pemasukans);
//          }
//          
//        }catch(Exception e){
//        JOptionPane.showMessageDialog(null,e);
//        }
//    }
//    
//    private void pengeluaran(){
//        try{
//          String sql="select SUM(keluar) AS pengeluaran from kas_keluar";
//          PreparedStatement pst = conn.prepareStatement(sql);
//          ResultSet rs=pst.executeQuery(sql);
//          
//          while(rs.next()){
//          String pengeluarans =rs.getString("pengeluaran");
//          if(pengeluarans == null){pengeluarans = "0";}
//          pengeluaran.setText(pengeluarans);
//          }
//          
//        }catch(Exception e){
//        JOptionPane.showMessageDialog(null,e);
//        }
//    }
//    
//    private void totalkas(){
//        int masuks,keluars,totals;
//        masuks = Integer.parseInt(pemasukan.getText());
//        keluars = Integer.parseInt(pengeluaran.getText());
//        totals = masuks-keluars;
//        total_kas.setText(String.valueOf(totals));
//    }
    private void pemasukan(){
        try{
          String sql="select SUM(masuk) AS pemasukan from kas_masuk";
          PreparedStatement pst = conn.prepareStatement(sql);
          ResultSet rs=pst.executeQuery(sql);
          
          while(rs.next()){
          String pemasukans =rs.getString("pemasukan");
          if(pemasukans == null){pemasukans = "0";}
          masuk1.setText(pemasukans);
          }
          
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void pengeluaran(){
        try{
          String sql="select SUM(keluar) AS pengeluaran from kas_keluar";
          PreparedStatement pst = conn.prepareStatement(sql);
          ResultSet rs=pst.executeQuery(sql);
          
          while(rs.next()){
          String pengeluarans =rs.getString("pengeluaran");
          if(pengeluarans == null){pengeluarans = "0";}
          keluar1.setText(pengeluarans);
          }
          
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void totalkas(){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        
        double masuks,keluars,totals;
        masuks = Double.parseDouble(masuk1.getText());
        keluars = Double.parseDouble(keluar1.getText());
        totals = masuks-keluars;
        String masukk = String.valueOf(kursIndonesia.format(masuks));
        String keluarr = String.valueOf(kursIndonesia.format(keluars));
        String saldos = String.valueOf(kursIndonesia.format(totals));
        pemasukan.setText(String.valueOf(masukk));
        pengeluaran.setText(String.valueOf(keluarr));
        total_kas.setText(String.valueOf(saldos));
    }
//    private void sort1(){
//    try {
//        generator = new Random();
//        DefaultTableModel tbl1 = new DefaultTableModel(); 
//        Connection kon =new koneksi().connect(); 
//        Statement stt1 =  kon.createStatement();
//          tbl1.addColumn("nip");
//          tbl1.addColumn("nama");
//          tbl1.addColumn("gaji_pokok");
//          tbl1.addColumn("tunjangan_jabatan");
//          tbl1.addColumn("upah_lembur");
//          tbl1.addColumn("total_potongan");
//          tbl1.addColumn("total_gaji");
//          String sql1 = "select dk.nip,dk.nama,jabatan.gaji_pokok,golongan.tunjangan_jabatan,lembur.upah_lembur,gaji.total_potongan,gaji.total_gaji from gaji inner join dk on gaji.nip = dk.nip inner join jabatan on gaji.kd_jabatan = jabatan.kd_jabatan inner join golongan on gaji.gol = golongan.gol inner join lembur on gaji.id_lembur = lembur.id_lembur ";
//          
//            ResultSet rs1= stt1.executeQuery(sql1);
//            while(rs1.next()){
//            tbl1.addRow(new Object[]{rs1.getString(1),rs1.getString(2),
//            rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7)});}            
//          sorter = new TableRowSorter<>(tbl1);
//          sorter.setComparator(1, new Comparator<Integer>() {
//              @Override
//              public int compare(Integer o1, Integer o2) {
//                  return o1.compareTo(o2);
//              }
//          });
//          tabel_gk.setModel(tbl1);
//          tabel_gk.setRowSorter(sorter);
//    } catch (SQLException ex) {
//        Logger.getLogger(pnl_gk.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_lkm = new javax.swing.JTable();
        rSButtonRiple1 = new rojeru_san.RSButtonRiple();
        rSButtonRiple3 = new rojeru_san.RSButtonRiple();
        nip = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        tgl1 = new com.toedter.calendar.JDateChooser();
        tgl2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pemasukan = new javax.swing.JLabel();
        pengeluaran = new javax.swing.JLabel();
        total_kas = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        masuk1 = new javax.swing.JLabel();
        keluar1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1010, 533));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("LAPORAN KAS MASUK");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        tabel_lkm.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_lkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_lkmMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_lkm);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 226, 757, 210));

        rSButtonRiple1.setBackground(new java.awt.Color(227, 227, 227));
        rSButtonRiple1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonRiple1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_print_20px.png"))); // NOI18N
        rSButtonRiple1.setText("CETAK DATA");
        rSButtonRiple1.setColorHover(new java.awt.Color(0, 204, 153));
        rSButtonRiple1.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonRiple1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRiple1ActionPerformed(evt);
            }
        });
        add(rSButtonRiple1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

        rSButtonRiple3.setBackground(new java.awt.Color(227, 227, 227));
        rSButtonRiple3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonRiple3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_20px.png"))); // NOI18N
        rSButtonRiple3.setText("CARI");
        rSButtonRiple3.setColorHover(new java.awt.Color(0, 204, 153));
        rSButtonRiple3.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonRiple3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRiple3ActionPerformed(evt);
            }
        });
        add(rSButtonRiple3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, 90, 40));

        nip.setPreferredSize(new java.awt.Dimension(0, 0));
        add(nip, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        tgl1.setDateFormatString("dd MMM yyyy");
        add(tgl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 130, -1));

        tgl2.setDateFormatString("dd MMM yyyy");
        add(tgl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 130, -1));

        jLabel8.setText("Pemasukan");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jLabel12.setText("Pengeluaran");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        jLabel9.setText("Total Kas");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        jLabel14.setText("s/d");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, -1, -1));

        jLabel15.setText("Tgl akhir");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, -1, -1));

        jLabel16.setText("Tgl awal");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 80, 10, 150));
        add(pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));
        add(pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));
        add(total_kas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Pilih rentang tanggal");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, -1, 20));

        masuk1.setText("jLabel1");
        add(masuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, -1, 0));

        keluar1.setText("jLabel3");
        add(keluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 10, 0));
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonRiple1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRiple1ActionPerformed
        tanggal_lkm tlkm = new tanggal_lkm();
        tlkm.setVisible(true);     
    }//GEN-LAST:event_rSButtonRiple1ActionPerformed

    private void tabel_lkmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_lkmMouseClicked

    }//GEN-LAST:event_tabel_lkmMouseClicked

    private void rSButtonRiple3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRiple3ActionPerformed
        cari();
    }//GEN-LAST:event_rSButtonRiple3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        datatable();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel keluar1;
    private javax.swing.JLabel masuk1;
    private javax.swing.JTextField nip;
    private javax.swing.JLabel pemasukan;
    private javax.swing.JLabel pengeluaran;
    private rojeru_san.RSButtonRiple rSButtonRiple1;
    private rojeru_san.RSButtonRiple rSButtonRiple3;
    public static javax.swing.JTable tabel_lkm;
    private com.toedter.calendar.JDateChooser tgl1;
    private com.toedter.calendar.JDateChooser tgl2;
    private javax.swing.JLabel total_kas;
    // End of variables declaration//GEN-END:variables
}
