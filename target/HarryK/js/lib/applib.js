(function (window, $) {

  "use strict";

  var document = window.document;
  var location = window.location;
  var console = window.console || {
      log: function () {}
  };

  var AppLib = window.AppLib = window.AppLib || {};
  
  AppLib.ready = false;
	    
  AppLib.constants = {
				"MESSAGE_POPUP":"MESSAGE_POPUP",
				"BIGDATA_POPUP":"BIGDATA_POPUP"
	    };
	
	/**
	 * Save Popup Window display
	 */
	AppLib.PopupWidget = (function() {
	
	    /* begin module */
	    var LCPOP = function () {
	        this.visible = false;
	        this.$container = null; // Reference of pop up main container
	        this.$background = null; // Reference of pop up background Container
	        this.$screens = null;   // All the screens that can be shown in pop up
	        this.$headingTitle = null; // Pop up Title
	        this.$popUpFrameHTML = null; // The HTML that layouts the frame of the HTML 
	        this.$messageHTML = null; // The HTML that layouts the message screen
	        this.$messageContainer = null; // Reference of the message container
	        this.$iconContainer = null; // Reference of the message icon container
	        this.$cpMessageWrapper = null; // Reference of the message wrapper container
	        this.$cpBigMessageContainer = null; // Reference of the big message container
	    };
	
	    /* add methods */
	    LCPOP.prototype = {
	        init: function () {
	        	LCPOP.$popUpFrameHTML ="<div id='CP_content'>" +
							        		"<div id='CP_title'>" +
							        			"<h1 id='cp_window_title'></h1>" +
							        			"<a href='javascript:void(0)' id='cp_close_btn'>" +
							        				"<span>Close</span>" +
							        			"</a>" +
							        		"</div>" +
							        		"<div id='CP_changeble_content'>" +
							        		"</div>" +
							        	"</div>";            							
					LCPOP.$messageHTML = "<div class='CP_inner_content' id='messagePopup'>" +  
								         	"<div id='icon_container'>" +
								         		"<div id='CP_error_img' class='changable_image'></div>" +
								         		"<div id='CP_warning_img' class='changable_image'></div>" +
								         		"<div id='CP_success_img' class='changable_image'></div>" +
								         	"</div>" +
								         	"<div class='message_container' id='msg_container_ele'>" +
								         		"<div id='ciber_message_wrapper'>"+
								         			"<p id='ciber_message'></p>" + 
								         		"</div>" +
								             "</div>" +
								             "<div class='button_div' id='ok_btn_container'>" +
								         		"<input type='button' id='ok_btn' class='btn btn-primary' value='Ok'/>" +
								         	"</div>" + 
								         "</div>";
					
					LCPOP.$bigDataHTML ="<div class='CP_inner_content' id='bigDataPopup'>" + 
											"<div class='cp_big_message_container' id='msg_para'>" +
								             "</div>"+
								             "<div class='button_div' id='close_btn_container'>" +
								         		"<input type='button' id='close_btn' class='btn btn-primary' value='Close'/>" +
								         	"</div>"+
							         	 "</div>";
					LCPOP.$popUpBackgroundHTML = "<div id='CP_container_background'></div>";
					LCPOP.$containerHTML = "<div id='CP_container'></div>"; 
					 $('body').append(LCPOP.$containerHTML);
					 
					LCPOP.$container = $("#CP_container");
					
		            $(LCPOP.$container).html(LCPOP.$popUpFrameHTML);
		            $('body').append(LCPOP.$popUpBackgroundHTML);
		            
	            LCPOP.$background = $("#CP_container_background");
	            LCPOP.$headingTitle = $("#cp_window_title");
	            LCPOP.$innerContent = $("#CP_changeble_content");
	            LCPOP.$innerContent.html(LCPOP.$messageHTML+LCPOP.$bigDataHTML);
	            LCPOP.$images = LCPOP.$container.find(".changable_image");
	            LCPOP.$screens = LCPOP.$container.find(".CP_inner_content");
	            LCPOP.$messageContainer = $("#msg_container_ele");
	            LCPOP.$iconContainer = $("#icon_container");
	            LCPOP.$cpMessageWrapper = $("#ciber_message_wrapper");
	            LCPOP.$cpBigMessageContainer = $(".cp_big_message_container");
	            
	            LCPOP.bindHandlers();
	        },
	
	        /**
	         * bindHandlers
	         */
	        bindHandlers: function () {
	            $(document).delegate("#cp_close_btn", "click", function (e) {
	                e.preventDefault();
	                LCPOP.close();
	            });
	            
	            $(document).delegate("#ok_btn", "click", function (e) {
	                e.preventDefault();
	                LCPOP.close();
	            });
	            
	            $(document).delegate("#close_btn", "click", function (e) {
	                e.preventDefault();
	                LCPOP.close();
	            });                
	          
	        },
	
	        setWindowHeading: function (headingText) {
	            LCPOP.$headingTitle.html(headingText);
	        },
	
	        hideAllIcons: function () {
	            LCPOP.$images.hide();
	        },
	
	        hideAllWindows: function () {
	            LCPOP.$screens.hide();
	        },
	
	        open: function (title, contentIds, contWidth, contHeight, windowType) {
	        	LCPOP.setWindowHeading(title);
	            LCPOP.hideAllWindows();
	            LCPOP.hideAllIcons();
	            
	            if(contentIds.length != 0){
	            	LCPOP.$container.find(contentIds[0]).show();
	            }
	        	
	            if (!LCPOP.visible) {
	                LCPOP.$background.show();
	                LCPOP.$container.show();
	                LCPOP.visible = true;
	            }
	            
	            LCPOP.resizeContainer(contWidth, contHeight);
	            
	            switch(windowType){
	            	case AppLib.constants.MESSAGE_POPUP :
	                    if(contentIds.length > 1){
	                    	LCPOP.$container.find(contentIds[1]).show();
	                    }
	                    
	            		LCPOP.alignMessageChildren(contWidth, contHeight);
	            		break;
	            		
	            	case AppLib.constants.BIGDATA_POPUP : 
	            		LCPOP.alignBigDataChildren(contWidth, contHeight);
	            		
	            		break;
	            }
	        },
	
	        close: function () {
	            if (LCPOP.visible) {
	                LCPOP.$background.hide();
	                LCPOP.$container.hide();
	                LCPOP.visible = false;
	            }
	        },
	        
	        /**
	         * loadErrorScreen
	         *
	         * Function - Loads the ERROR Screen
	         */
	        loadErrorScreen: function (msg) {
	        	LCPOP.$cpMessageWrapper.html("<p id='ciber_message'>"+msg+"</p>");
	            LCPOP.open("Error", ["#messagePopup","#CP_error_img"],500, 200, AppLib.constants.MESSAGE_POPUP);
	        },
	        
	
	        /**
	         * loadValidationErrorScreen
	         *
	         * Function - Loads the validation ERROR Screen
	         */
	        loadValidationErrorScreen: function (msg) {
	        	LCPOP.$cpMessageWrapper.html("<p id='ciber_message'>"+msg+"</p>");
	            LCPOP.open("Validation Error", ["#messagePopup","#CP_error_img"],500, 250, AppLib.constants.MESSAGE_POPUP);
	        },
	
	        /**
	         * loadWarningScreen
	         *
	         * Function - Loads the SUCCESS Screen
	         */
	        loadWarningScreen: function (msg) {
	        	LCPOP.$cpMessageWrapper.html("<p id='ciber_message'>"+msg+"</p>");
	            LCPOP.open("Warning", ["#messagePopup","#CP_warning_img"],500, 200, AppLib.constants.MESSAGE_POPUP);
	        },
	        
	        /**
	         * loadSuccessScreen
	         *
	         * Function - Loads the SUCCESS Screen
	         */
	        loadSuccessScreen: function (msg) {
	        	LCPOP.$cpMessageWrapper.html("<p id='ciber_message'>"+msg+"</p>");
	            LCPOP.open("Success",["#messagePopup", "#CP_success_img"],500, 200, AppLib.constants.MESSAGE_POPUP);
	        },
	        
	        /**
	         * loadBigDataScreen
	         *
	         * Function - Loads the BigDataScreen Screen
	         */
	        loadBigDataScreen: function (title, msg) {
	        	LCPOP.$cpBigMessageContainer.html(msg);
	            LCPOP.open(title,["#bigDataPopup"],800, 600, AppLib.constants.BIGDATA_POPUP);
	        },            
	        
	        computeWidth:function(ele, isExt)
	        {
	    		var compWidth;
	    		compWidth =  (parseInt($(ele).css('padding-left').replace("px", ""))+
	    				parseInt($(ele).css('padding-right').replace("px", ""))+
	    				parseInt($(ele).css('margin-right').replace("px", ""))+
	    				parseInt($(ele).css('margin-left').replace("px", ""))+
	    				parseInt($(ele).css('border-left-width').replace("px", ""))+
	    				parseInt($(ele).css('border-right-width').replace("px", "")));
	    		if(isExt)
	    		{
	    			compWidth = compWidth + $(ele).width();
	    		}
	    		
	    		return compWidth;
	        },
	    
		      computeHeight:function(ele, isExt)
		      {
				var compHeight =  (parseInt($(ele).css('padding-top').replace("px", ""))+
						parseInt($(ele).css('padding-bottom').replace("px", ""))+
						parseInt($(ele).css('margin-top').replace("px", ""))+
						parseInt($(ele).css('margin-bottom').replace("px", ""))+
						parseInt($(ele).css('border-top-width').replace("px", ""))+
						parseInt($(ele).css('border-bottom-width').replace("px", "")));
					
				if(isExt)
				{
					compHeight = compHeight + $(ele).height();
				}
					
				return compHeight;
		      },
		    	
			resizeContainer:function(contWidth, contHeight)
			{
				  contWidth = contWidth + 'px';
				  contHeight = contHeight + 'px';
				  
				  $(LCPOP.$container).addClass("js-fix-per").css({
				    "width" : contWidth,
				    "height" : contHeight
				  });	  
				  
				  var hWide = ( $(LCPOP.$container).width())/2; 
				  var hTall = ( $(LCPOP.$container).height())/2; 
				
				  hWide = '-' + hWide + 'px';
				  hTall = '-' + hTall + 'px';
				
				  $(LCPOP.$container).addClass("js-fix").css({
				    "margin-left" : hWide,
				    "margin-top" : hTall
				  });
		    		  
			},
		    	
	  	alignMessageChildren:function(contWidth, contHeight)
	  	{
	  		  var ok_div_height = LCPOP.computeHeight('#ok_btn_container',true);
	  		  
	  		  $(LCPOP.$iconContainer).height(contHeight-(LCPOP.computeHeight(LCPOP.$headingTitle, true)+LCPOP.computeHeight(LCPOP.$iconContainer, false)+ok_div_height));
	  		  $(LCPOP.$messageContainer).width(contWidth-(LCPOP.computeWidth(LCPOP.$iconContainer,true)+LCPOP.computeWidth(LCPOP.$messageContainer, false)));
	  		  $(LCPOP.$messageContainer).height(contHeight -(LCPOP.computeHeight(LCPOP.$headingTitle,true)+LCPOP.computeHeight(LCPOP.$messageContainer, false)+ok_div_height));
	  		  
	  		  $(LCPOP.$cpMessageWrapper).css("margin-top",($(LCPOP.$cpMessageWrapper).parent().height() - $(LCPOP.$cpMessageWrapper).height())/2 +10+ 'px');
	  		  $(LCPOP.$images).css("margin-top",($(LCPOP.$images).parent().height() - $(LCPOP.$images).height())/2 +10+ 'px');
	  	},
	  	
	  	alignBigDataChildren:function(contWidth, contHeight)
	  	{
	  		  var close_div_height = LCPOP.computeHeight('#close_btn_container',true);
	  		  
	  		  $(LCPOP.$cpBigMessageContainer).height(contHeight-(LCPOP.computeHeight(LCPOP.$headingTitle,true)+close_div_height));
	  		  $(LCPOP.$cpBigMessageContainer).width(contWidth-(LCPOP.computeWidth(LCPOP.$cpBigMessageContainer,false)));
	  	}
	    };
	
	    /* return public properties */
	    LCPOP = new LCPOP();
	    return LCPOP;
	
	}());
  
  /**
   * AppLib active home menu module
   */
  AppLib.ActiveHomeMenuWidget = (function () {
      /* begin module */
      var LCAHMW = function(){};      

      /* add methods */
      LCAHMW.prototype = {
          /**
           * init
           * Set user-specific values
           */
          init:function()
          {
        	  this.bindHandlers();
          },
          
	      /**
	       * bindHandlers
	       */
	      bindHandlers:function()
	      {
		  		var mainButton = $("a[rel='main_nav_a']");
		  		mainButton.unbind("click", this.invokeAddActiveMenu);
		  		mainButton.bind("click", this.invokeAddActiveMenu);
		  		
		  		var sideNavButton = $("a[rel='side_nav_a']");
		  		sideNavButton.unbind("click", this.invokeSideActiveMenu);
		  		sideNavButton.bind("click", this.invokeSideActiveMenu);
	      },
	      
	      invokeSideActiveMenu:function(){
	    	  setTimeout(LCAHMW.addActiveSideMenu,100);
	      },
	      
	      invokeAddActiveMenu:function(){
	    	  setTimeout(LCAHMW.addActiveMenu,100);
	      },
	      
	      addActiveSideMenu:function(){
	    	  LCAHMW.removeSideNavClass();
	    	  var nav = document.getElementById('side-navigation');
	    	  var anchor = nav.getElementsByTagName('a');
	    	  var angularUrldata = window.location.href.split("#");
	    	  var currentUrldata, current = "";
	    	  
	    	  if(angularUrldata != null && angularUrldata.length>1)
	    	  {
	    		  if(angularUrldata[1] != "/")
	    		  {
	    			  if(angularUrldata[1].indexOf('/') == 0)
	    			  {
	    				  angularUrldata[1] = angularUrldata[1].substring(1,angularUrldata[1].length);
	    			  }
		    		  currentUrldata = angularUrldata[1].split("/");
		    		  if(currentUrldata != null && currentUrldata.length>0){
		    			  current = currentUrldata[1];
		    		  }
	    		  }
	    	  }
	    	  
	    	  for (var i = 0; i < anchor.length; i++)
	    	  {
	    		  var anchorUrldata = (anchor[i].href).split("/");
	    		  var  anchorUrl = "";
	    		  var urlAfterHash;
	    		  
	    		  /*if(anchorUrldata != null && anchorUrldata.length>1)
		    	  {
	    			  if(anchorUrldata[1] != "/")
		    		  {
		    			  if(anchorUrldata[1].indexOf('/') == 0)
		    			  {
		    				  anchorUrldata[1] = anchorUrldata[1].substring(1,anchorUrldata[1].length);
		    			  }
			    		  
		    			  urlAfterHash = anchorUrldata[1].split("/");
			    		  if(urlAfterHash != null && urlAfterHash.length>0){
			    			  anchorUrl = urlAfterHash[0];
			    		  }
		    		  }
		    	  }*/
	    		  anchorUrl = anchorUrldata[anchorUrldata.length-1];
	    		  
	    		  if(anchorUrl == current)
	    		  {
		    		  $(anchor[i]).addClass("active");
	    		  }
	    	  }
	      },
	      
	      addActiveMenu:function()
	      {
	    	  LCAHMW.removeNavClass();
	    	  var nav = document.getElementById('main_nav');
	    	  var anchor = nav.getElementsByTagName('a');
	    	  var angularUrldata = window.location.href.split("#");
	    	  var currentUrldata, current = "";
	    	  
	    	  if(angularUrldata != null && angularUrldata.length>1)
	    	  {
	    		  if(angularUrldata[1] != "/")
	    		  {
	    			  if(angularUrldata[1].indexOf('/') == 0)
	    			  {
	    				  angularUrldata[1] = angularUrldata[1].substring(1,angularUrldata[1].length);
	    			  }
		    		  currentUrldata = angularUrldata[1].split("/");
		    		  if(currentUrldata != null && currentUrldata.length>0){
		    			  current = currentUrldata[0];
		    		  }
	    		  }
	    	  }
	    	  
	    	  for (var i = 0; i < anchor.length; i++)
	    	  {
	    		  var anchorUrldata = (anchor[i].href).split("#");
	    		  var  anchorUrl = "";
	    		  var urlAfterHash;
	    		  
	    		  if(anchorUrldata != null && anchorUrldata.length>1)
		    	  {
	    			  if(anchorUrldata[1] != "/")
		    		  {
		    			  if(anchorUrldata[1].indexOf('/') == 0)
		    			  {
		    				  anchorUrldata[1] = anchorUrldata[1].substring(1,anchorUrldata[1].length);
		    			  }
			    		  
		    			  urlAfterHash = anchorUrldata[1].split("/");
			    		  if(urlAfterHash != null && urlAfterHash.length>0){
			    			  anchorUrl = urlAfterHash[0];
			    		  }
		    		  }
		    	  }
	    		  if(anchorUrl == current)
	    		  {
		    		  var liTag = anchor[i].parentElement;
		    		  $(liTag).addClass("active");
	    		  }
	    	  }
	    	  

	      },
	        
	        removeNavClass:function()
	        {
	        	var nav = document.getElementById('main_nav');
	        	var liArray = nav.children;
	        	if(liArray)
        		{
	        		for(var i=0;i<liArray.length;i++)
        			{
	        			$(liArray[i]).removeClass("active");
        			}
        		}
	        },
	      
	      removeSideNavClass:function()
	      {
	        	var nav = document.getElementById('side-navigation');
	        	var liArray = nav.children;
	        	if(liArray)
        		{
	        		for(var i=0;i<liArray.length;i++)
        			{
	        			$(liArray[i]).removeClass("active");
        			}
        		}
	      }
      };

	  /* return public properties */
	  LCAHMW = new LCAHMW();
	  return LCAHMW;
  	}());
  
  /**
   * Loading widget
   */
  AppLib.LoadingWidget = (function () {

      /* begin module */
      var LCLW = function () {
          this.visible = false;
          this.$container = null; // Reference of the loader container
          this.$loaderWrapper = null;
      };

      /* add methods */
      LCLW.prototype = {
          init: function () {
          	LCLW.$loaderHTML ="<div id='CP_loader_content'>" +
          						"<div id='CP_loader_wrapper'>" +
          							"<img src='images/preloader_transparent.gif' width='100px' height='100px'/>"+
          						"</div>"+
          					"</div>"; 
				
	            $('body').append(LCLW.$loaderHTML);
	            LCLW.$container = $("#CP_loader_content");
	            LCLW.$loaderWrapper = $("#CP_loader_wrapper");
              LCLW.bindHandlers();
          },

          /**
           * bindHandlers
           */
          bindHandlers: function () {
          	// Nothing do here as of now.
          },

          show: function () {
              if (!LCLW.visible) {
              	LCLW.alignLoader();
                  LCLW.$container.show();
                  LCLW.visible = true;
              }
          },

          hide: function () {
              if (LCLW.visible) {
              	LCLW.$container.hide();
                  LCLW.visible = false;
              }
          },
          
	    	
	    	alignLoader:function()
	    	{
	    		  var hWide = ($(LCLW.$container).width())/2; 
	    		  var hTall = ($(LCLW.$container).height())/2; 
	    		  
	    		  var imageWidth = ($(LCLW.$loaderWrapper).width())/2;
	    		  var imageHeight = ($(LCLW.$loaderWrapper).height())/2;
	    		  
	    		  var image_left_margin = hWide - imageWidth + 'px';
	    		  var image_right_margin  = hTall - imageHeight + 'px';
	    		
	    		  $(LCLW.$loaderWrapper).addClass("js-fix").css({
	    		    "margin-left" : image_left_margin,
	    		    "margin-top" : image_right_margin
	    		  });
	    		  
	    	}
          
      };

      /* return public properties */
      LCLW = new LCLW();
      return LCLW;

  }());
  
  /**
   * Loading widget
   */
  AppLib.LoginWidget = (function () {

      /* begin module */
      var LCLWT = function () {
    	  
      };

      /* add methods */
      LCLWT.prototype = {
		init: function () {
			LCLWT.bindHandlers();
		},
		
		/**
		* bindHandlers
		*/
		bindHandlers: function () {
			$("#login").off( "click", LCLWT.loginUser);
			$("#login").on( "click", LCLWT.loginUser);
		},

		loginUser:function(event)
		{
			var username = $("#username").val();
			var password = $("#password").val();
			
			var url = 'j_spring_security_check';
			
			var formData = {
					"j_username" : username,
					"j_password" : password
			    };
			    
			    var props = {
		    	   type:"POST",
				   data:formData,
		           async: false
			   };
			
		    /*var formData = {
				"password" : password,
				"username" : username
		    };*/
			
		    /*var props = {
		    		type:"POST",
		            data: JSON.stringify(formData),
		           dataType:"json",
		            contentType: "application/json; charset=utf-8",
		            async: false
		       };*/
			
		    var success = function (data, status, jqXHR) {
		        var status = jqXHR.status;
		        
		        /*if(data != null && data.result == "success")
    			{
		        	window.location.href = "index.html";
    			}
		        else
		        {
		        	AppLib.PopupWidget.loadErrorScreen("Please enter valid username/password");
		        }*/
		        if(status == 200)
    			{
		        	window.location.href = "home.html";
    			}
		        else
		        {
		        	AppLib.PopupWidget.loadErrorScreen("Please enter valid username/password");
		        }
		    };
		
		    var error = function (jqXHR, type, status) {
		        var status = jqXHR.status;
		        AppLib.PopupWidget.loadErrorScreen("Something bad happend, please try after some time");
		    };
		    
		    LCLWT.makeRequest(url, props, success, error);
		},
          
          /**
           * makeRequest
           *
           * Makes all AJAX requests for this module
           * @param {String} url The request URL
           * @param {Object} props AJAX request properties
           * @param {Function} success Callback
           * @param {Function} error Callback
           */
          makeRequest:function (url, props, success, error) {
              if (typeof error === "undefined") {
                  error = this.defaultErrorHandler;
              }
              
              
              return $.ajax(url, props).done(success).fail(error);
          },
          
          /**
           * defaultErrorHandler
           *
           * Default error callback for AJAX requests
           */
          defaultErrorHandler:function (jqxhr, type, status) {
              throw new Error(status);
          }          
          
      };

      /* return public properties */
      LCLWT = new LCLWT();
      return LCLWT;

  }());
  
  AppLib.init = function () {
	if (AppLib.ready) 
	{
	    return;
	}
	
	AppLib.PopupWidget.init();
	AppLib.LoadingWidget.init();
	AppLib.ActiveHomeMenuWidget.init();
	AppLib.LoginWidget.init();
	AppLib.ready = true;
  };

  $(document).ready(AppLib.init);

}(window, jQuery));