﻿/*** RED5 Open Source Flash Server - http://www.osflash.org/red5** Copyright (c) 2006-2009 by respective authors (see below). All rights reserved.** This library is free software; you can redistribute it and/or modify it under the* terms of the GNU Lesser General Public License as published by the Free Software* Foundation; either version 2.1 of the License, or (at your option) any later* version.** This library is distributed in the hope that it will be useful, but WITHOUT ANY* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A* PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.** You should have received a copy of the GNU Lesser General Public License along* with this library; if not, write to the Free Software Foundation, Inc.,* 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA*/// ** AUTO-UI IMPORT STATEMENTS **import org.red5.utils.Connector;// ** END AUTO-UI IMPORT STATEMENTS **import org.red5.net.Stream;//import org.red5.utils.Delegate;import flash.external.*;import com.blitzagency.xray.util.XrayLoader;class org.red5.samples.livestream.subscriber.Main extends MovieClip {// Constants:	public static var CLASS_REF = org.red5.samples.livestream.subscriber.Main;	public static var LINKAGE_ID:String = "org.red5.samples.livestream.subscriber.Main";// Public Properties:// Private Properties:	private var stream:Stream;	private var cam:Camera;    private var streamName:String;// UI Elements:// ** AUTO-UI ELEMENTS **	private var connector:Connector;	private var publish_video:Video;// ** END AUTO-UI ELEMENTS **// Initialization:	private function Main() {XrayLoader.loadConnector("xray.swf");}	private function onLoad():Void { configUI(); }// Public Methods:// Semi-Private Methods:// Private Methods:	private function configUI():Void	{		this.streamName = String(_level0.streamName);		// setup cam		cam = Camera.get();		// get notified of connection changes		connector.addEventListener("connectionChange", this);		// initialize the connector		connector.configUI();	}	private function connectionChange(evtObj:Object):Void	{		if(evtObj.connected)		{			// setup stream			stream = new Stream(evtObj.connection);			stream.play(this.streamName, -1);			publish_video.attachVideo(stream);		} else		{			stream.close();			publish_video.attachVideo(null);			publish_video.clear();		}	}}