package explorer;

import helper.ModelViewFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import commands.CommandHistory;

public class Explorer  {

	private static Explorer instance = null;
	public static synchronized Explorer getInstance() {
		if (instance == null) { 
			instance = new Explorer();
		}
		return instance;
	}
	
	private ExplorerView view;
	private int selectedStockIndex = -1;
	
	private Explorer() {
		view = new ExplorerView();
	}
	
	public ExplorerView getView() {
		return view;
	}
	
	public void setSelectedStock(int id, boolean editMode) {
		view.setEditMode(editMode);
		selectedStockIndex = id;
		view.setSelected();
		System.out.println("Selected stock: " + selectedStockIndex);
	}
	
	public int getSelected() {
		return selectedStockIndex;
	}
	
	public void clearSelected() {
		selectedStockIndex = -1;
		view.clearSelected();
	}
	
	@SuppressWarnings("serial")
	class ExplorerView extends JPanel {
		
		/* Layout */
		private final Dimension componentDimension = new Dimension(175, 40);
		private final Dimension smallRigidArea = new Dimension(0, 20);
		private final Dimension largeRigidArea = new Dimension(200, 40);
		private final EmptyBorder emptyBorder = new EmptyBorder(0,0,0,0);
		private static final float xAlign = 0.5f;
		
		//The controls on the right hand side.  Name, Expression, currentValue and stop condition
		final JTextField txtName = new JTextField();	
		final JTextArea txtExpr = new JTextArea();
		final JTextField txtCurrentValue = new JTextField();		
		final JTextField txtStopcondition = new JTextField();		
		
		public ExplorerView() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.attachChildren();
			this.setBackground(new Color(213, 221, 226));
			this.setEditMode(false);
		}
		
		public void setEditMode(boolean editMode) {
			txtName.setEnabled(editMode);
			txtExpr.setEnabled(editMode);
			txtCurrentValue.setEnabled(editMode);
			txtStopcondition.setEnabled(editMode);
		}

		private void attachChildren() {			
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Flow Explorer"));		
			add(Box.createRigidArea(smallRigidArea));		
			addNameSection();		
			add(Box.createRigidArea(largeRigidArea));		
			addExpressionSection();		
			add(Box.createRigidArea(largeRigidArea));		
			addCurrentValueSection();		
			add(Box.createRigidArea(new Dimension(0, 20)));			
			addStopConditionSection();
			add(Box.createRigidArea(new Dimension(0, 60)));
		}
		
		private void addNameSection() {					
			txtName.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					CommandHistory.getInstance().changeStockName(selectedStockIndex, txtName.getText());
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Name"), txtName);
		}
		
		private void addExpressionSection() {
			txtExpr.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					// TODO Auto-generated method stub
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Expression"), txtExpr);
		}	
		
		private void addCurrentValueSection() {
			txtCurrentValue.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					// TODO Auto-generated method stub
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Current Value"), txtCurrentValue);
		}
		
		private void addStopConditionSection() {
			txtStopcondition.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					// TODO Auto-generated method stub
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Stop Condition"), txtStopcondition);
		}
		
		private void decorateAndAdd(Border border, Dimension maxSize, float alignX, JComponent... components) {
			for(JComponent c : components) {
				c.setMaximumSize(maxSize);
				c.setAlignmentX(alignX);
				c.setBorder(border);
				add(c);
			}
		}
		
		public void setSelected() {
			this.txtName.setText(ModelViewFactory.getName(selectedStockIndex));
			//this.txtExpr.setText(selectedStock.getExpression());	
		}
		
		public void clearSelected() {
			this.txtName.setText(null);
			this.txtExpr.setText(null);
			this.txtCurrentValue.setText(null);
			this.txtStopcondition.setText(null);
		}
	}
}
