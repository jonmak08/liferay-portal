define("frontend-js-metal-web@1.0.0/metal-position/src/Position-min", ["exports","metal/src/metal","./Geometry"], function(t,e,n){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});var r=i(e),f=i(n),u=function(){function t(){o(this,t)}return (t.getClientHeight=function(t){return this.getClientSize_(t,"Height")}, t.getClientSize_=function(t,e){var n=t;return (r["default"].isWindow(t)&&(n=t.document.documentElement), r["default"].isDocument(t)&&(n=t.documentElement), n["client"+e])}, t.getClientWidth=function(t){return this.getClientSize_(t,"Width")}, t.getDocumentRegion_=function(t){var e=this.getHeight(t),n=this.getWidth(t);return this.makeRegion(e,e,0,n,0,n)}, t.getHeight=function(t){return this.getSize_(t,"Height")}, t.getOffsetLeft=function(e,n){return e.offsetLeft+(n?0:t.getTranslation(e).left)}, t.getOffsetTop=function(e,n){return e.offsetTop+(n?0:t.getTranslation(e).top)}, t.getRegion=function(t,e){return r["default"].isDocument(t)||r["default"].isWindow(t)?this.getDocumentRegion_(t):this.makeRegionFromBoundingRect_(t.getBoundingClientRect(),e)}, t.getScrollLeft=function(t){return r["default"].isWindow(t)?t.pageXOffset:r["default"].isDocument(t)?t.defaultView.pageXOffset:t.scrollLeft}, t.getScrollTop=function(t){return r["default"].isWindow(t)?t.pageYOffset:r["default"].isDocument(t)?t.defaultView.pageYOffset:t.scrollTop}, t.getSize_=function(t,e){if(r["default"].isWindow(t))return this.getClientSize_(t,e);if(r["default"].isDocument(t)){var n=t.documentElement;return Math.max(t.body["scroll"+e],n["scroll"+e],t.body["offset"+e],n["offset"+e],n["client"+e])}return Math.max(t["client"+e],t["scroll"+e],t["offset"+e])}, t.getTransformMatrixValues=function(t){var e=getComputedStyle(t),n=e.msTransform||e.transform||e.webkitTransform||e.mozTransform;if("none"!==n){for(var i=[],o=/([\d-\.\s]+)/g,r=o.exec(n);r;)i.push(r[1]),r=o.exec(n);return i}}, t.getTranslation=function(e){var n=t.getTransformMatrixValues(e),i={left:0,top:0};return (n&&(i.left=parseFloat(6===n.length?n[4]:n[13]),i.top=parseFloat(6===n.length?n[5]:n[14])), i)}, t.getWidth=function(t){return this.getSize_(t,"Width")}, t.intersectRegion=function(t,e){return f["default"].intersectRect(t.top,t.left,t.bottom,t.right,e.top,e.left,e.bottom,e.right)}, t.insideRegion=function(t,e){return e.top>=t.top&&e.bottom<=t.bottom&&e.right<=t.right&&e.left>=t.left}, t.insideViewport=function(t){return this.insideRegion(this.getRegion(window),t)}, t.intersection=function(t,e){if(!this.intersectRegion(t,e))return null;var n=Math.min(t.bottom,e.bottom),i=Math.min(t.right,e.right),o=Math.max(t.left,e.left),r=Math.max(t.top,e.top);return this.makeRegion(n,n-r,o,i,r,i-o)}, t.makeRegion=function(t,e,n,i,o,r){return{bottom:t,height:e,left:n,right:i,top:o,width:r}}, t.makeRegionFromBoundingRect_=function(e,n){var i=n?t.getScrollLeft(document):0,o=n?t.getScrollTop(document):0;return this.makeRegion(e.bottom+o,e.height,e.left+i,e.right+i,e.top+o,e.width)}, t.pointInsideRegion=function(e,n,i){return t.insideRegion(i,t.makeRegion(n,0,e,e,n,0))}, t)}();t["default"]=u});