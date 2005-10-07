/*
 * GroupCustomizer.java
 *
 * Created on January 13, 2005, 9:13 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.openide.explorer.propertysheet.editors.EnhancedCustomPropertyEditor;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.*;
import de.berlios.nblayoutpack.formlayout.editors.*;

/**
 *
 * @author  Illya Kysil
 */
public class GroupCustomizer extends JPanel implements EnhancedCustomPropertyEditor{

  private GroupCustomizerContext context;
  private LayoutTableModel layoutTableModel;
  
  /** Creates new form GroupCustomizer */
  public GroupCustomizer(GroupCustomizerContext context){
    this.context = context;
    initComponents();
    context.initSpecLabel(specLabel);
    layoutTableModel = new LayoutTableModel();
    layoutTable.setModel(layoutTableModel);
    DefaultTableCellRenderer numberCellRenderer = new DefaultTableCellRenderer();
    numberCellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
    layoutTable.getColumnModel().getColumn(0).setPreferredWidth(36);
    layoutTable.getColumnModel().getColumn(0).setCellRenderer(numberCellRenderer);
    layoutTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    layoutTable.getSelectionModel().addListSelectionListener(new TableListener());
    updateData();
  }
  
  public Object getPropertyValue() throws java.lang.IllegalStateException{
    return GroupsEditor.parseGroups(getCustomizerValue());
  }

  protected String getCustomizerValue(){
    String[] data = layoutTableModel.getData();
    StringBuffer sb = new StringBuffer();
    boolean isFirst = true;
    for(int i = 0; i < data.length; i++){
      if(isFirst){
        isFirst = false;
      }
      else{
        sb.append(";");
      }
      sb.append(data[i]);
    }
    return sb.toString();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  private void initComponents() {//GEN-BEGIN:initComponents
    specLabel = new javax.swing.JLabel();
    specField = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    layoutTable = new javax.swing.JTable();
    addButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();

    com.jgoodies.forms.layout.FormLayout _formLayoutInstance = new com.jgoodies.forms.layout.FormLayout("4dlu,250px:grow,8dlu,p,4dlu", "4dlu,p,4dlu,p,8dlu,p,8dlu,p,0:grow,4dlu");
    _formLayoutInstance.setColumnGroups(new int[][]{});
    _formLayoutInstance.setRowGroups(new int[][]{});
    setLayout(_formLayoutInstance);

    specLabel.setDisplayedMnemonic('C');
    specLabel.setLabelFor(specField);
    specLabel.setText("Column/row group:");
    add(specLabel, new com.jgoodies.forms.layout.CellConstraints(2, 2, 3, 1, com.jgoodies.forms.layout.CellConstraints.DEFAULT, com.jgoodies.forms.layout.CellConstraints.DEFAULT, new java.awt.Insets(0, 0, 0, 0)));

    specField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        specFieldActionPerformed(evt);
      }
    });

    add(specField, new com.jgoodies.forms.layout.CellConstraints(2, 4, 3, 1, com.jgoodies.forms.layout.CellConstraints.DEFAULT, com.jgoodies.forms.layout.CellConstraints.DEFAULT, new java.awt.Insets(0, 0, 0, 0)));

    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    layoutTable.setModel(new javax.swing.table.DefaultTableModel(
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
    layoutTable.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        layoutTableKeyPressed(evt);
      }
    });

    jScrollPane1.setViewportView(layoutTable);

    add(jScrollPane1, new com.jgoodies.forms.layout.CellConstraints(2, 6, 1, 4, com.jgoodies.forms.layout.CellConstraints.DEFAULT, com.jgoodies.forms.layout.CellConstraints.DEFAULT, new java.awt.Insets(0, 0, 0, 0)));

    addButton.setMnemonic('A');
    addButton.setText("Add");
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });

    add(addButton, new com.jgoodies.forms.layout.CellConstraints(4, 6, 1, 1, com.jgoodies.forms.layout.CellConstraints.DEFAULT, com.jgoodies.forms.layout.CellConstraints.DEFAULT, new java.awt.Insets(0, 0, 0, 0)));

    deleteButton.setMnemonic('D');
    deleteButton.setText("Delete");
    deleteButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteButtonActionPerformed(evt);
      }
    });

    add(deleteButton, new com.jgoodies.forms.layout.CellConstraints(4, 8, 1, 1, com.jgoodies.forms.layout.CellConstraints.DEFAULT, com.jgoodies.forms.layout.CellConstraints.DEFAULT, new java.awt.Insets(0, 0, 0, 0)));

  }//GEN-END:initComponents

  private void layoutTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_layoutTableKeyPressed
    if((evt.getKeyCode() == KeyEvent.VK_SPACE) && (evt.getModifiers() == 0)){
      specField.requestFocusInWindow();
      specField.selectAll();
    }
    if((evt.getKeyCode() == KeyEvent.VK_DELETE) && (evt.getModifiers() == 0)){
      delete(layoutTable.getSelectedRow());
    }
  }//GEN-LAST:event_layoutTableKeyPressed

  private void specFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specFieldActionPerformed
    replace(layoutTable.getSelectedRow());
  }//GEN-LAST:event_specFieldActionPerformed

  private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    delete(layoutTable.getSelectedRow());
  }//GEN-LAST:event_deleteButtonActionPerformed

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    insert(0, layoutTableModel.getData().length);
  }//GEN-LAST:event_addButtonActionPerformed

  protected void insert(int position, int delta){
    String spec = specField.getText();
    try{
      context.checkGroup(spec);
    }
    catch(Exception e){
      specField.requestFocusInWindow();
      return;
    }
    ArrayList list = new ArrayList(Arrays.asList(layoutTableModel.getData()));
    list.add(position + delta, spec);
    layoutTableModel.setData((String[])list.toArray(new String[list.size()]));
    layoutTable.getSelectionModel().setSelectionInterval(position + delta, position + delta);
    specField.requestFocusInWindow();
    specField.selectAll();
  }
  
  protected void replace(int position){
    if(position < 0){
      insert(0, layoutTableModel.getData().length);
      return;
    }
    String spec = specField.getText();
    try{
      context.checkGroup(spec);
    }
    catch(Exception e){
      specField.requestFocusInWindow();
      return;
    }
    String[] data = layoutTableModel.getData();
    data[position] = spec;
    layoutTableModel.setData(data);
    layoutTable.getSelectionModel().setSelectionInterval(position, position);
    specField.requestFocusInWindow();
    specField.selectAll();
  }
  
  protected void delete(int position){
    ArrayList list = new ArrayList(Arrays.asList(layoutTableModel.getData()));
    list.remove(position);
    String[] data = (String[])list.toArray(new String[list.size()]);
    layoutTableModel.setData(data);
    int selRow = Math.min(position, data.length - 1);
    layoutTable.getSelectionModel().setSelectionInterval(selRow, selRow);
    specField.requestFocusInWindow();
    specField.selectAll();
  }
  
  public void updateData(){
    String value = context.getValue();
    String[] data = value.split(";");
    String[] cData = new String[data.length];
    int cDataLength = 0;
    for(int i = 0; i < data.length; i++){
      if(!data[i].trim().equals("")){
        cData[cDataLength++] = data[i].trim();
      }
    }
    String[] mData = new String[cDataLength];
    System.arraycopy(cData, 0, mData, 0, cDataLength);
    layoutTableModel.setData(mData);
    updateButtons();
  }
  
  public void updateButtons(){
    int selRow = layoutTable.getSelectedRow();
    deleteButton.setEnabled(selRow >= 0);
  }
    
  /**
   * Getter for property context.
   * @return Value of property context.
   */
  public GroupCustomizerContext getContext(){
    return context;
  }
  
  /**
   * Setter for property context.
   * @param context New value of property context.
   */
  public void setContext(GroupCustomizerContext context){
    this.context = context;
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JButton deleteButton;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable layoutTable;
  private javax.swing.JTextField specField;
  private javax.swing.JLabel specLabel;
  // End of variables declaration//GEN-END:variables

  private class LayoutTableModel extends AbstractTableModel{

    private String[] layoutData;
    
    void setData(String[] data){
      layoutData = data;
      fireTableDataChanged();
//      String customizerValue = getCustomizerValue();
//      if(!customizerValue.equalsIgnoreCase(getValue())){
//        setValue(customizerValue);
//      }
    }
    
    String[] getData(){
      return layoutData;
    }
    
    // --------
    
    public int getRowCount(){
      return layoutData == null ? 0 : layoutData.length;
    }
    
    public int getColumnCount(){
      return 2;
    }
    
    public Object getValueAt(int row, int column){
      switch (column){
        case 0: return new Integer(row + 1);
        case 1: return layoutData[row];
      }
      throw new ArrayIndexOutOfBoundsException();
    }
    
    public Class getColumnClass(int column){
      switch (column) {
        case 0: return Integer.class;
        case 1: return String.class;
      }
      throw new ArrayIndexOutOfBoundsException();
    }
    
    public String getColumnName(int column){
      switch (column){
        case 0:
        case 1: return "";
      }
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  private class TableListener implements ListSelectionListener{
    
    public void valueChanged(ListSelectionEvent e){
      int selRow = layoutTable.getSelectedRow();
      if(selRow < 0){
        specField.setText("");
      }
      else{
        specField.setText(layoutTableModel.getData()[selRow]);
      }
      updateButtons();
    }
    
  }
  
}
