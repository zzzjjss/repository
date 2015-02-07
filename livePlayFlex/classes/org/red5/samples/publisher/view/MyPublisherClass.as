package org.red5.samples.publisher.view
{
	import mx.containers.Box;
	
	import org.red5.samples.publisher.events.SetupConnectionEvent;
	import org.red5.samples.publisher.events.SetupDevicesEvent;
	import org.red5.samples.publisher.events.SetupStreamsEvent;
	import org.red5.samples.publisher.model.Main;
	import org.red5.samples.publisher.model.ModelLocator;
	import org.red5.samples.publisher.model.Navigation;

	public class MyPublisherClass extends Box
	{
		/**
		 * 
		 */		
		private var model : ModelLocator = ModelLocator.getInstance();
		
		[Bindable]
		/**
		 * 
		 */		
		public var main : Main = model.main;
		
		[Bindable]
		/**
		 * 
		 */		
		public var navigation : Navigation = model.navigation;
		
		/**
		 * 
		 */		
		public function MyPublisherClass()
		{
			var devicesEvent : SetupDevicesEvent = new SetupDevicesEvent();
			devicesEvent.dispatch();
			
			var connectionEvent : SetupConnectionEvent = new SetupConnectionEvent();
			connectionEvent.dispatch();
			
			var streamsEvent : SetupStreamsEvent = new SetupStreamsEvent();
			streamsEvent.dispatch();
		}	
		
	}
}