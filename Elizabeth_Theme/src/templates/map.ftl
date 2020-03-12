
<div id="googleMap" style="width:100%;height:400px;"></div>

<script>
function myMap() {
var mapProp= {
  center:new google.maps.LatLng(33.997866, -117.814449),
  zoom:18,
};
var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBuGv4SFr4qdaYwOPJUdh9a04iRGTR8cmU&callback=myMap"></script>