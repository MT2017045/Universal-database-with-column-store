
function drawAccidentArea(dirService,map) { 
	//var sample='{"latitude":"12.850548624202743","longitude":"77.66848800969126"}';
	/*var json = (function () {
	    var json = null;
	    $.ajax({
	        'async': false,
	        'global': false,
	        'url': "/home/hanshika/eclipse-workspace/gis/sample.json",
	        'dataType': "json",
	        'success': function (sample) {
	            json = sample;
	        }
	    });
	    return json;
	})();*/
	//var mydata = JSON.parse(sample);
	//alert(json);
	//alert(json);
	 var source= "12.80257395457377, 77.7060644168854";
	 var dest="12.808265278584953, 77.69816799354555";
	
		var polylineOptionsActual = {
              	      strokeColor: '#FF0000',
              	      strokeOpacity: 1.0,
              	      strokeWeight: 5
              	      };
              	    
               
               var dirRenderer2 = new google.maps.DirectionsRenderer({suppressMarkers: true , polylineOptions: polylineOptionsActual});
              
                var request2 = {
                        origin:source,
                        destination:dest,
                        travelMode: google.maps.TravelMode.DRIVING
                    };
                    dirService.route(request2, function(result, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            dirRenderer2.setDirections(result);
                        }
                    });
                    dirRenderer2.setMap(map);
                    map.panTo(google.maps.LatLng(setEnd()));
                    
                    var cascadiaFault = new google.maps.Polyline({
                        path: [
                          source,dest
                        ]
                      });
}