// Copyright 2020 Gabor Kokeny and contributors
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.vaadin.addon.leaflet4vaadin.demo.view.map;

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.map.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.MapOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Caption("Map events")
@Route(value = "mapevents", layout = LeafletDemoApp.class)
public class MapEventsExample extends ExampleContainer {

	private TextArea eventLogs;

	@Override
	protected void initMap(final Div mapContainer) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();

		VerticalLayout sidebar = new VerticalLayout();
		sidebar.setHeightFull();
		sidebar.setWidth("400px");
		eventLogs = new TextArea();
		eventLogs.getStyle().set("font-size", "12px");
		eventLogs.setSizeFull();
		eventLogs.setClearButtonVisible(true);

		sidebar.add(eventLogs);

		final MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.204101562500004));
		options.setZoom(7);
		final LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
		leafletMap.onClick(this::logEvent);
		leafletMap.onMoveEnd(this::logEvent);
		leafletMap.onMouseOut(this::logEvent);
		leafletMap.onResize(this::logEvent);
		leafletMap.onDoubleClick(this::logEvent);
		leafletMap.onContextMenuOpened(this::logEvent);
		leafletMap.onMouseDown(this::logEvent);
		leafletMap.onMouseUp(this::logEvent);
		leafletMap.onMouseOver(this::logEvent);
		leafletMap.onZoomLevelsChange(this::logEvent);

		leafletMap.onZoom(this::logEvent);
		leafletMap.onZoomStart(this::logEvent);
		leafletMap.onZoomEnd(this::logEvent);

		layout.add(leafletMap, sidebar);
		mapContainer.add(layout);
	}

	private void logEvent(LeafletEvent leafletEvent) {
		StringBuilder sb = new StringBuilder(eventLogs.getValue());
		sb.append("\n");
		sb.append(leafletEvent.getType());
		eventLogs.setValue(sb.toString());
	}

}