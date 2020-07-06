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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nikx
 */
public class pnl_dkk extends javax.swing.JPanel {
private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    users users = new users();
    /**
     * Creates new form pnl_home
     */
    public pnl_dkk() {
        initComponents();
        aktif();
        pemasukan();
        pengeluaran();
        totalkas();
        kosong();
        transaksi();
        datatable();        
    }

    protected void aktif(){
        no_kk.setEnabled(true);
        nm_trans.setEnabled(true);
        keluar.setEnabled(true);
        tgl_keluar.setEnabled(true);
        ket.setEnabled(true);
    }
    
    protected void kosong(){
        no_kk.setText("");
        nm_trans.setSelectedItem("");
        nm_trans.insertItemAt("", 0);
        tgl_keluar.setCalendar(null);
        keluar.setText("");
        ket.setText("");
    }
    
    
    protected void datatable(){
        Object[] Baris ={"ID Kas Keluar","Tanggal Keluar","Nama Transaksi","Keterangan","Jumlah Keluar"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_k.setModel(tabmode);
        String sql = "select * from kas_keluar inner join transaksi on kas_keluar.id_transaksi = transaksi.id_transaksi ORDER BY tgl_kk ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("no_kk");
                String b = hasil.getString("tgl_kk");
                String c = hasil.getString("nm_transaksi");
                String d = hasil.getString("ket_keluar");
                String e = hasil.getString("keluar");
            
                String[] data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        } catch (Exception e){}
    }
    
    protected void cari(){
        java.util.Date utilStartDate = tgl_awal.getDate();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        java.util.Date utilStartDate2 = tgl_akhir.getDate();
        java.sql.Date sqlStartDate2 = new java.sql.Date(utilStartDate2.getTime());
        jLabel10.setText(sqlStartDate.toString() +" s/d "+sqlStartDate2.toString());
        Object[] Baris ={"ID Kas Keluar","Tanggal Keluar","Nama Transaksi","Keterangan","Jumlah Keluar"};
        tabmode = new DefaultTableModel(null, Baris);
        tabel_k.setModel(tabmode);
        try {
        String sql = "select * from kas_keluar inner join transaksi on kas_keluar.id_transaksi = transaksi.id_transaksi"
                + " WHERE tgl_km BETWEEN '" + sqlStartDate.toString() + "' AND '" + sqlStartDate2.toString() + "'"
                + " ORDER BY tgl_kk ASC";
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
    
    private void transaksi(){
        try{
          String sql="select * from transaksi";
          PreparedStatement pst = conn.prepareStatement(sql);
          ResultSet rs=pst.executeQuery(sql);
          
          while(rs.next()){
          String trans =rs.getString("nm_transaksi");
          nm_trans.addItem(trans);
          }
          
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_k = new javax.swing.JTable();
        rSButtonRiple1 = new rojeru_san.RSButtonRiple();
        rSButtonRiple2 = new rojeru_san.RSButtonRiple();
        rSButtonRiple3 = new rojeru_san.RSButtonRiple();
        no_kk = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tgl_keluar = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nm_trans = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        keluar = new javax.swing.JTextField();
        change = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pemasukan = new javax.swing.JLabel();
        pengeluaran = new javax.swing.JLabel();
        total_kas = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tgl_awal = new com.toedter.calendar.JDateChooser();
        tgl_akhir = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        masuk1 = new javax.swing.JLabel();
        keluar1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1010, 533));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("DATA KAS KELUAR");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, -1, -1));

        tabel_k.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_k.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_kMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_k);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 850, 200));

        rSButtonRiple1.setBackground(new java.awt.Color(227, 227, 227));
        rSButtonRiple1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonRiple1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_delete_filled_20px_1.png"))); // NOI18N
        rSButtonRiple1.setText("HAPUS DATA");
        rSButtonRiple1.setColorHover(new java.awt.Color(0, 204, 153));
        rSButtonRiple1.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonRiple1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRiple1ActionPerformed(evt);
            }
        });
        add(rSButtonRiple1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 450, -1, -1));

        rSButtonRiple2.setBackground(new java.awt.Color(227, 227, 227));
        rSButtonRiple2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonRiple2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_file_filled_20px.png"))); // NOI18N
        rSButtonRiple2.setText("UBAH DATA");
        rSButtonRiple2.setColorHover(new java.awt.Color(0, 204, 153));
        rSButtonRiple2.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonRiple2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRiple2ActionPerformed(evt);
            }
        });
        add(rSButtonRiple2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

        rSButtonRiple3.setBackground(new java.awt.Color(227, 227, 227));
        rSButtonRiple3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSButtonRiple3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_plus_filled_20px.png"))); // NOI18N
        rSButtonRiple3.setText("TAMBAH DATA");
        rSButtonRiple3.setColorHover(new java.awt.Color(0, 204, 153));
        rSButtonRiple3.setColorText(new java.awt.Color(0, 0, 0));
        rSButtonRiple3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonRiple3ActionPerformed(evt);
            }
        });
        add(rSButtonRiple3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, -1, -1));

        no_kk.setPreferredSize(new java.awt.Dimension(0, 0));
        add(no_kk, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        jLabel1.setText("Tanggal Keluar");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel5.setText("Nama Transaksi");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        tgl_keluar.setDateFormatString("dd MMM yyyy");
        add(tgl_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 150, -1));

        jLabel6.setText("Keterangan");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, -1, -1));

        jLabel7.setText("Jumlah Keluar");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        nm_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nm_transMouseClicked(evt);
            }
        });
        nm_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_transActionPerformed(evt);
            }
        });
        add(nm_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 160, -1));

        ket.setColumns(20);
        ket.setRows(5);
        jScrollPane1.setViewportView(ket);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 230, 40));
        add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 180, -1));

        change.setText("UBAH");
        change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeActionPerformed(evt);
            }
        });
        add(change, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, -1, -1));

        jLabel8.setText("Pemasukan");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, -1, -1));

        jLabel9.setText("Total Kas");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        pemasukan.setText("0");
        add(pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, -1));

        pengeluaran.setText("0");
        add(pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, -1, -1));

        total_kas.setText("0");
        add(total_kas, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 80, -1, 150));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 220, 10));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 110, -1, 120));

        jLabel12.setText("Pengeluaran");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 188, -1, 20));

        jLabel14.setText("Tgl awal");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, -1, 20));

        jLabel15.setText("Tgl akhir");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, -1, -1));

        tgl_awal.setDateFormatString("dd MMM yyyy");
        add(tgl_awal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, -1, -1));

        tgl_akhir.setDateFormatString("dd MMM yyyy");
        add(tgl_akhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, -1, -1));

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, -1, -1));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 600, 10));
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 60, 20));

        masuk1.setText("jLabel3");
        add(masuk1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 0, 0));

        keluar1.setText("jLabel4");
        add(keluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, -1, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Mode Pencarian");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Masukkan Data");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonRiple3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRiple3ActionPerformed
        String sql = "insert into kas_keluar (tgl_kk,id_transaksi,ket_keluar,keluar) values (?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);            
            java.util.Date utilStartDate = tgl_keluar.getDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            stat.setDate(1, sqlStartDate);
            
            String trans="select * from transaksi where nm_transaksi='"+nm_trans.getSelectedItem()+"'";
            PreparedStatement pst11 = conn.prepareStatement(trans);
            ResultSet rs11=pst11.executeQuery(trans);
            rs11.next();
            String a =rs11.getString("id_transaksi");
            stat.setString(2, a);
         
            stat.setString(3, ket.getText());
            stat.setString(4, keluar.getText());
            
            stat.executeUpdate();
            kosong();
            datatable();
            pemasukan();
            pengeluaran();
            totalkas();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
        
    }//GEN-LAST:event_rSButtonRiple3ActionPerformed

    private void rSButtonRiple1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRiple1ActionPerformed
       int ok = JOptionPane.showConfirmDialog(null, "Yakin untuk di hapus ?","Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
       if (ok==0){
           String sql = "delete from kas_keluar where no_kk ='"+no_kk.getText()+"'";
           try {
               PreparedStatement stat = conn.prepareStatement(sql);
               stat.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
               kosong();
               datatable();
           }catch (SQLException e){
               JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
           }
       }
    }//GEN-LAST:event_rSButtonRiple1ActionPerformed

    private void tabel_kMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_kMouseClicked
        int bar = tabel_k.getSelectedRow();
       String a = tabmode.getValueAt(bar, 0).toString();
       String c = tabmode.getValueAt(bar, 2).toString();
       String d = tabmode.getValueAt(bar, 3).toString();
       String e = tabmode.getValueAt(bar, 4).toString();
       
       no_kk.setText(a);
       try {
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabmode.getValueAt(bar, 1));
        tgl_keluar.setDate(date);
    } catch (ParseException ex) {
        Logger.getLogger(pnl_dkk.class.getName()).log(Level.SEVERE, null, ex);
    }
       
       nm_trans.setSelectedItem(c);
       ket.setText(d);
       keluar.setText(e);
    }//GEN-LAST:event_tabel_kMouseClicked

    private void rSButtonRiple2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonRiple2ActionPerformed
       String sql = "update kas_keluar set tgl_kk=?,id_transaksi=?,ket_keluar=?,keluar=? where no_kk=?";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            java.util.Date utilStartDate = tgl_keluar.getDate();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            stat.setDate(1, sqlStartDate);
            
            String trans="select * from transaksi where nm_transaksi='"+nm_trans.getSelectedItem()+"'";
            PreparedStatement pst11 = conn.prepareStatement(trans);
            ResultSet rs11=pst11.executeQuery(trans);
            rs11.next();
            String a =rs11.getString("id_transaksi");
            stat.setString(2, a);
            
            stat.setString(3, ket.getText());
            stat.setString(4, keluar.getText());
            stat.setString(5, no_kk.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            datatable();            
            pemasukan();
            pengeluaran();
            totalkas();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
        }
    }//GEN-LAST:event_rSButtonRiple2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        datatable();
        kosong();
        jLabel10.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeActionPerformed
        transaksi trans = new transaksi();
        trans.setVisible(true);        
    }//GEN-LAST:event_changeActionPerformed

    private void nm_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_transActionPerformed
        
    }//GEN-LAST:event_nm_transActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cari();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nm_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nm_transMouseClicked
       //transaksi();
    }//GEN-LAST:event_nm_transMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton change;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField keluar;
    private javax.swing.JLabel keluar1;
    private javax.swing.JTextArea ket;
    private javax.swing.JLabel masuk1;
    private javax.swing.JComboBox<String> nm_trans;
    private javax.swing.JTextField no_kk;
    private javax.swing.JLabel pemasukan;
    private javax.swing.JLabel pengeluaran;
    private rojeru_san.RSButtonRiple rSButtonRiple1;
    private rojeru_san.RSButtonRiple rSButtonRiple2;
    private rojeru_san.RSButtonRiple rSButtonRiple3;
    javax.swing.JTable tabel_k;
    private com.toedter.calendar.JDateChooser tgl_akhir;
    private com.toedter.calendar.JDateChooser tgl_awal;
    private com.toedter.calendar.JDateChooser tgl_keluar;
    private javax.swing.JLabel total_kas;
    // End of variables declaration//GEN-END:variables
}
