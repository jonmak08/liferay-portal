define("frontend-js-metal-web@1.0.0/metal-position/src/Align-min", ["exports","./Position"], function(t,e){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});var n=i(e),g=function(){function t(){o(this,t)}return (t.align=function(t,e,i){var o=this.suggestAlignBestRegion(t,e,i),g=o.region,r=window.getComputedStyle(t,null);if("fixed"!==r.getPropertyValue("position")){g.top+=window.pageYOffset,g.left+=window.pageXOffset;for(var f=t;f=f.offsetParent;)g.top-=n["default"].getOffsetTop(f),g.left-=n["default"].getOffsetLeft(f)}return (t.style.top=g.top+"px", t.style.left=g.left+"px", o.position)}, t.getAlignBestRegion=function(e,i,o){return t.suggestAlignBestRegion(e,i,o).region}, t.getAlignRegion=function(e,i,o){var g=n["default"].getRegion(i),r=n["default"].getRegion(e),f=0,h=0;switch(o){case t.TopCenter:f=g.top-r.height,h=g.left+g.width/2-r.width/2;break;case t.RightCenter:f=g.top+g.height/2-r.height/2,h=g.left+g.width;break;case t.BottomCenter:f=g.bottom,h=g.left+g.width/2-r.width/2;break;case t.LeftCenter:f=g.top+g.height/2-r.height/2,h=g.left-r.width;break;case t.TopRight:f=g.top-r.height,h=g.right-r.width;break;case t.BottomRight:f=g.bottom,h=g.right-r.width;break;case t.BottomLeft:f=g.bottom,h=g.left;break;case t.TopLeft:f=g.top-r.height,h=g.left}return{bottom:f+r.height,height:r.height,left:h,right:h+r.width,top:f,width:r.width}}, t.isValidPosition=function(t){return t>=0&&8>=t}, t.suggestAlignBestRegion=function(t,e,i){for(var o=0,g=i,r=this.getAlignRegion(t,e,g),f=g,h=r,s=n["default"].getRegion(window),a=0;8>a;){if(n["default"].intersectRegion(s,h)){var l=n["default"].intersection(s,h),u=l.width*l.height;if(u>o&&(o=u,r=h,g=f),n["default"].insideViewport(h))break}f=(i+ ++a)%8,h=this.getAlignRegion(t,e,f)}return{position:g,region:r}}, t)}();g.TopCenter=0,g.TopRight=1,g.RightCenter=2,g.BottomRight=3,g.BottomCenter=4,g.BottomLeft=5,g.LeftCenter=6,g.TopLeft=7,g.Top=g.TopCenter,g.Right=g.RightCenter,g.Bottom=g.BottomCenter,g.Left=g.LeftCenter,t["default"]=g});