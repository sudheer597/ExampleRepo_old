package com.kewill.view;

import java.util.Iterator;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.DragAndDropWrapper.WrapperTargetDetails;
import com.vaadin.ui.DragAndDropWrapper.WrapperTransferable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class GridView extends VerticalLayout implements View {
	public static final String NAME = "";

	public GridView() {
		System.out.println("came");
		final GridLayout grid = new GridLayout(2, 2);
		TextField field1 = new TextField("Field1");
		TextField field2 = new TextField("Field2");
		TextField field3 = new TextField("Field3");
		TextField field4 = new TextField("Field4");

		grid.addComponent(field1);
		grid.addComponent(field2);
		grid.addComponent(field3);
		grid.addComponent(field4);

		DragAndDropWrapper sourceWrapper = new DragAndDropWrapper(grid);
		sourceWrapper.setDragStartMode(DragStartMode.COMPONENT);

		sourceWrapper.setDropHandler(new DropHandler() {

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}

			@Override
			public void drop(DragAndDropEvent event) {
				WrapperTargetDetails targetDetails = (WrapperTargetDetails) event
						.getTargetDetails();
				WrapperTransferable trasferable = (WrapperTransferable) event
						.getTransferable();
				Component field = (Component)trasferable.getSourceComponent();
				/*GridLayout.Area area = grid.getComponentArea(field);
				
				System.out.println(area.getColumn1());
				System.out.println(area.getColumn2());
				System.out.println(area.getRow1());
				System.out.println(area.getRow2());
				*/
				DropTarget target = targetDetails.getTarget();
				Iterator<Component> itr = grid.iterator();
				Component next=itr.next();
				int index =0;
				while(next!=target && itr.hasNext()){
					next = itr.next();
					if (next != field) {
                        index++;
                    } 				}
				System.out.println(index);
			}
		});
		addComponent(sourceWrapper);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
