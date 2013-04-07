package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import aux.StockModelViewFactory;

@SuppressWarnings("serial")
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
	
	public void setSelectedStock(Integer id) {
		selectedStockIndex = id;
		System.out.println("Selected: " + selectedStockIndex);
	}
	
	class ExplorerView extends JPanel {
		
		/* Layout */
		private final Dimension componentDimension = new Dimension(175, 40);
		private final Dimension smallRigidArea = new Dimension(0, 20);
		private final Dimension largeRigidArea = new Dimension(200, 40);
		private final EmptyBorder emptyBorder = new EmptyBorder(0,0,0,0);
		private static final float xAlign = 0.5f;
		
		public ExplorerView() {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.attachChildren();
			this.setBackground(new Color(213, 221, 226));
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
			final JTextField txtName = new JTextField();	
						
			txtName.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					if (selectedStockIndex != -1) {
						StockModelViewFactory.previewName(selectedStockIndex, txtName.getText());
					}
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Name"), txtName);
		}
		
		private void addExpressionSection() {
			JTextArea txtExpr = new JTextArea();
			txtExpr.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					// TODO Auto-generated method stub
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Expression"), txtExpr);
		}	
		
		private void addCurrentValueSection() {
			JTextField txtCurrentValue = new JTextField();		
			txtCurrentValue.getDocument().addUndoableEditListener(new UndoableEditListener() {
				@Override
				public void undoableEditHappened(UndoableEditEvent e) {
					// TODO Auto-generated method stub
				}
			});
			decorateAndAdd(emptyBorder, componentDimension, xAlign, new JLabel("Current Value"), txtCurrentValue);
		}
		
		private void addStopConditionSection() {
			JTextField txtStopcondition = new JTextField();		
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
	}
}
