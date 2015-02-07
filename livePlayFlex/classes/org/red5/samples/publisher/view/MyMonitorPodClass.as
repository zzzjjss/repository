package org.red5.samples.publisher.view
{
	import flash.external.ExternalInterface;
	import flash.net.ObjectEncoding;
	import flash.utils.setTimeout;
	
	import mx.containers.Box;
	import mx.controls.Alert;
	import mx.controls.Button;
	import mx.controls.ComboBox;
	import mx.controls.LinkButton;
	import mx.controls.NumericStepper;
	import mx.controls.TextInput;
	import mx.events.CloseEvent;
	import mx.events.ListEvent;
	
	import org.red5.samples.publisher.events.ChangePresetEvent;
	import org.red5.samples.publisher.events.CloseConnectionEvent;
	import org.red5.samples.publisher.events.CreatePresetEvent;
	import org.red5.samples.publisher.events.OpenDocsEvent;
	import org.red5.samples.publisher.events.PublishStreamEvent;
	import org.red5.samples.publisher.events.RemovePresetsEvent;
	import org.red5.samples.publisher.events.StartCameraEvent;
	import org.red5.samples.publisher.events.StartConnectionEvent;
	import org.red5.samples.publisher.events.StartMicrophoneEvent;
	import org.red5.samples.publisher.events.StopCameraEvent;
	import org.red5.samples.publisher.events.StopMicrophoneEvent;
	import org.red5.samples.publisher.events.UnpublishStreamEvent;
	import org.red5.samples.publisher.model.Logger;
	import org.red5.samples.publisher.model.Main;
	import org.red5.samples.publisher.model.ModelLocator;
	import org.red5.samples.publisher.model.Navigation;

	public class MyMonitorPodClass extends Box
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
		
		[Bindable]
		/**
		 * 
		 */		
		public var logger : Logger = model.logger;
		
		[Bindable]
		/**
		 * 
		 */		
		public var host_txt : TextInput;
		
		public var audio_btn : Button;		
		public var audio_txt : LinkButton;	
		public var video_btn : Button;	
		public var startLivePlay_btn:Button;
		public var video_txt : LinkButton;
		public var buffer_ns : NumericStepper;	
		public var type_cb : ComboBox;	
		public var presets_cb : ComboBox;
		public var bw_txt : LinkButton;	
		public var bw_ns : NumericStepper;		
		public var encoding_cb : ComboBox;	
		public var camera_cb : ComboBox;	
		public var fps_ns : NumericStepper;	
		public var fps_txt : LinkButton;
		public var gain_txt : LinkButton;		
		public var gain_ns : NumericStepper;	
		public var width_ns : NumericStepper;		
		public var width_txt : LinkButton;	
		public var height_ns : NumericStepper;
		public var height_txt : LinkButton;
		public var keyframe_ns : NumericStepper;
		public var keyframe_txt : LinkButton;	
		public var level_ns : NumericStepper;	
		public var level_txt : LinkButton;	
		public var microphone_cb : ComboBox;		
		public var quality_ns : NumericStepper;	
		public var quality_txt : LinkButton;	
		public var rate_ns : NumericStepper;			
		public var rate_txt : LinkButton;
		public var proxy_cb : ComboBox;	
		public var timeout_ns : NumericStepper;		
		public var timeout_txt : LinkButton;
		public function controlLivePlay():void
		{
			
			main.startLivePlay_btn=startLivePlay_btn;
			
			if(startLivePlay_btn.label=="开始直播"){
				startLivePlay_btn.enabled=false;
				var selectedCamIndex:Number = camera_cb.selectedIndex;
				var selectedMicIndex : Number = microphone_cb.selectedIndex;
				if(selectedCamIndex==0||selectedCamIndex==-1){
					Alert.show("请选择摄像头！","错误")
					startLivePlay_btn.enabled=true;
					return ;
				}
				if(selectedMicIndex==0||selectedMicIndex==-1){
					Alert.show("请选择麦克风！","错误")
					startLivePlay_btn.enabled=true;
					return ;
				}
				setupCamera();
				setupMicrophone();
				setupConnection();
				startLivePlay_btn.enabled=true;
			}else{
				startLivePlay_btn.enabled=false;
				var unpublish:UnpublishStreamEvent =new UnpublishStreamEvent();
				unpublish.dispatch();
				var closeCon:CloseConnectionEvent =new CloseConnectionEvent();
				closeCon.dispatch();
				var stopCamera:StopCameraEvent=new StopCameraEvent();
				stopCamera.dispatch();
				var stopMic:StopMicrophoneEvent =new StopMicrophoneEvent();
				stopMic.dispatch();
				startLivePlay_btn.label="开始直播";
				startLivePlay_btn.enabled=true;	
			}
			
		}
		
		/**
		 * Creates and closes the connection with the server.
		 * <p>The method is invoked when the connect Button is pressed and the <code>StringValidator</code>
		 * for the host field is validated.</p>
		 */		
		public function setupConnection() : void
		{
			// Get AMF type from the encoding_cb Combobox.
			var encodingType : uint =ObjectEncoding.AMF0;
			//
			var url:String=ExternalInterface.call("getRtmpUrl");  
			
			var hostName : String = url;
			//
			var proxyType : String = "none";
			
			// Broadcast the event with Cairngorm.
			// Pass the host name, proxy type and encoding type to the StartConnectionEvent.
			var startConnectionEvent : StartConnectionEvent = new StartConnectionEvent( 
				hostName,
				proxyType,
				encodingType
			);
			
			startConnectionEvent.dispatch();
			
			// Create connection with server.
//			if ( main.connectButtonLabel == main.btnConnect ) 
//			{
//				
//				
//			} 
//			else
//				// Close connection with the server.
//			{
//				// Broadcast the event with Cairngorm.
//				var closeConnectionEvent : CloseConnectionEvent = new CloseConnectionEvent();
//				closeConnectionEvent.dispatch();
//			}	
			
			
		}
		
		/**
		 * 
		 * @param path
		 */		
		public function openLink( path : String ) : void 
		{	
			// Open documentation url.
			var event : OpenDocsEvent = new OpenDocsEvent( path, main.docsURL );
			event.dispatch();
		}
		
		
		
		
		/**
		 * 
		 * 
		 */		
		public function setupCamera() : void 
		{
			var selectedCamIndex:Number = camera_cb.selectedIndex;
			var keyframeInterval : Number = 5;
			var videoWidth : Number = 320;
			var videoHeight : Number = 240;
			var fps : Number = 15;
			var bandwidth : Number =0;
			var frameQuality : Number = 0;
			// No video.
			if ( selectedCamIndex == 0 ) 
			{
				// stop camera
				var stopCameraEvent : StopCameraEvent = new StopCameraEvent();
				stopCameraEvent.dispatch();
				
			} 
			else if ( selectedCamIndex > -1 )
			{
				//
				//video_btn.label = "Apply";
				// start camera
				var startCameraEvent : StartCameraEvent = new StartCameraEvent( selectedCamIndex,
					keyframeInterval,
					videoWidth,
					videoHeight,
					fps,
					bandwidth,
					frameQuality );
				startCameraEvent.dispatch();
			}
		}
		
		/**
		 * 
		 * 
		 */		
		public function setupMicrophone() : void 
		{
			var selectedMicIndex : Number = microphone_cb.selectedIndex;
			var gain : Number = 50;
			var bitrate : Number =22;
			var silenceLevel : Number = 10;
			var silenceTimeout : Number = 2000;
			// No audio.
			if ( selectedMicIndex == 0 ) 
			{
				// stop microphone
				var stopMicrophoneEvent : StopMicrophoneEvent = new StopMicrophoneEvent();
				stopMicrophoneEvent.dispatch();
			} 
			else if ( selectedMicIndex > -1 )
			{
				//
				//audio_btn.label = "Apply";
				// start microphone
				var startMicrophoneEvent : StartMicrophoneEvent = new StartMicrophoneEvent( selectedMicIndex,
					gain,
					bitrate,
					silenceLevel,
					silenceTimeout );
				startMicrophoneEvent.dispatch();
			}
		}
		
		/**
		 * On creationComplete of the <code>server</code> Canvas this method is triggered.
		 */		
		public function initializeServerSettings() : void 
		{
			//
			changePreset( 0 );
			//
			main.images.serverLogo = type_cb.selectedItem.img;
		}	
		
		/**
		 * 
		 * 
		 */		
		private function removePresets() : void 
		{
			// remove presets
			var removePresetsEvent : RemovePresetsEvent = new RemovePresetsEvent();
			removePresetsEvent.dispatch();		
			// refresh view
			initializeServerSettings();
		}
		
		/**
		 * 
		 * @param index
		 */		
		private function changePreset( index : Number ) : void 
		{	
			var emptyPresetIndex : int = main.serverPresets.length - 3;
			var savePresetIndex : int = main.serverPresets.length - 2;
			var deletePresetsIndex : int = main.serverPresets.length - 1;
			//
			if ( index == emptyPresetIndex ) {
				// Unable to select this item, return to first one.
				var invalidPresetEvent : ChangePresetEvent = new ChangePresetEvent( 0 );
				invalidPresetEvent.dispatch();	
			}
				// Change preset.
			else if ( index < savePresetIndex ) 
			{
				// change preset.
				var changePresetEvent : ChangePresetEvent = new ChangePresetEvent( index );
				changePresetEvent.dispatch();			
				
			} 
				// Save preset.
			else if ( index == savePresetIndex ) 
			{
				//
				var hostName : String = host_txt.text;
				// Get AMF type from the encoding_cb Combobox.
				var encodingType : int = encoding_cb.selectedIndex;
				//
				var proxytype : int = proxy_cb.selectedIndex;
				//
				var serverType : int = type_cb.selectedIndex;
				// show popup to save the created preset.
//				var createPresetEvent : CreatePresetEvent = new CreatePresetEvent( this, hostName, 
//					serverType, encodingType,
//					proxytype );
//				createPresetEvent.dispatch();
			} 
				// Delete presets.
			else if ( index == deletePresetsIndex ) 
			{
				// show popup to delete presets.
				if ( main.serverPresets.length > main.orgServerPresets.length ) 
				{
					// ask for confirmation.
					confirmClearSO();
				}
				else 
				{
					// Reset list view to most recent preset.
					navigation.selectedPreset = 0;	
				}
			}
		}
		
		/**
		 * 
		 * 
		 */		
		private function confirmClearSO() : void 
		{
			//
			Alert.show( "Are you sure you want to remove your server presets?", 
				"Remove Presets", 3, this, alertClickHandler );
		}
		
		/**
		 * Event handler function for displaying the selected Alert button.
		 * 
		 * @param event
		 */		
		private function alertClickHandler( event : CloseEvent ) : void 
		{
			if ( event.detail == Alert.YES ) 
			{
				//
				removePresets();
			}
			else 
			{
				// Reset list view to most recent preset.
				navigation.selectedPreset = 0;
			}
		}
		
		/**
		 * 
		 * @param event
		 */		
		public function presetsHandler( event : ListEvent ) : void 
		{
			//
			changePreset( event.target.selectedIndex );
		}
		
		/**
		 * 
		 * 
		 */		
		public function showSaveWindow() : void 
		{
			
		}
		
		/**
		 * Set init values for video settings components.
		 */		
		public function initializeVideoSettings() : void
		{
			// VIDEO
			video_txt.label = "Device  ";
			bw_txt.label = "Bandwidth  ";
			fps_txt.label = "FPS  ";
			width_txt.label = "Width  ";
			height_txt.label = "Height  ";
			quality_txt.label = "Quality  ";
			keyframe_txt.label = "Keyframe  ";
			// 'bandwidth' numeric stepper
			bw_ns.minimum = 0;
			bw_ns.maximum = 1000000;
			bw_ns.stepSize = 100;
			bw_ns.value = main.videoSettings.bandwidth;
			// 'fps' numeric stepper
			fps_ns.minimum = 1;
			fps_ns.maximum = 30;
			fps_ns.stepSize = 1;
			fps_ns.value = main.videoSettings.fps;
			// 'width' numeric stepper
			width_ns.minimum = 1;
			width_ns.maximum = 800;
			width_ns.stepSize = 1;
			width_ns.value = main.videoSettings.width;
			// 'height' numeric stepper
			height_ns.minimum = 1;
			height_ns.maximum = 600;
			height_ns.stepSize = 1;
			height_ns.value = main.videoSettings.height;
			// 'quality' numeric stepper
			quality_ns.minimum = 0;
			quality_ns.maximum = 100;
			quality_ns.stepSize = 1;
			quality_ns.value = main.videoSettings.quality;
			// 'keyframe' numeric stepper
			keyframe_ns.minimum = 1;
			keyframe_ns.maximum = 100;
			keyframe_ns.stepSize = 1;
			keyframe_ns.value = main.videoSettings.keyframe;
			//
			buffer_ns.minimum = 0;
			buffer_ns.maximum = 100;
			buffer_ns.stepSize = 0.1;
			buffer_ns.value = main.generalSettings.bufferTime;
		}
		
		/**
		 * Set init values for audio settings components.
		 * <p>Method runs once on creationComplete of the <code>audio</code> Canvas.</p>
		 */		
		public function initializeAudioSettings() : void
		{
			// AUDIO
			audio_txt.label = "Device  ";
			rate_txt.label="Khz ";
			gain_txt.label="Gain ";
			level_txt.label="Silence ";
			timeout_txt.label="Timeout ";
			// 'rate' numeric stepper
			rate_ns.minimum = 8;
			rate_ns.maximum = 44;
			rate_ns.stepSize = 1;
			rate_ns.value = main.audioSettings.rate;
			// 'gain_ns' numeric stepper
			gain_ns.minimum = 0;
			gain_ns.maximum = 100;
			gain_ns.stepSize = 1;
			gain_ns.value = main.audioSettings.gain;
			// 'level_ns' numeric stepper
			level_ns.minimum = 0;
			level_ns.maximum = 100;
			level_ns.stepSize = 1;
			level_ns.value = main.audioSettings.level;
			// 'timeout_ns' numeric stepper
			timeout_ns.minimum = 0;
			timeout_ns.maximum = 10000;
			timeout_ns.stepSize = 1;
			timeout_ns.value = main.audioSettings.timeout;
		}
		
	}
}