(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-00c4bc30"],{"19cb":function(t,e,n){"use strict";n("2af9")},"2af9":function(t,e,n){},"2ca0":function(t,e,n){"use strict";var a=n("23e7"),i=n("06cf").f,o=n("50c4"),c=n("5a34"),s=n("1d80"),r=n("ab13"),l=n("c430"),u="".startsWith,p=Math.min,h=r("startsWith"),f=!l&&!h&&!!function(){var t=i(String.prototype,"startsWith");return t&&!t.writable}();a({target:"String",proto:!0,forced:!f&&!h},{startsWith:function(t){var e=String(s(this));c(t);var n=o(p(arguments.length>1?arguments[1]:void 0,e.length)),a=String(t);return u?u.call(e,a,n):e.slice(n,n+a.length)===a}})},"5b9d":function(t,e,n){},"64c0":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("el-row",[n("el-select",{staticStyle:{"min-width":"200px"},attrs:{disabled:""},model:{value:t.upyunConfig.bucketName,callback:function(e){t.$set(t.upyunConfig,"bucketName",e)},expression:"upyunConfig.bucketName"}}),n("el-cascader",{staticStyle:{"min-width":"450px"},attrs:{placeholder:"请选择目录",options:t.pathArr,props:t.pathProps},model:{value:t.activePath,callback:function(e){t.activePath=e},expression:"activePath"}}),n("el-button",{attrs:{type:"primary",size:"medium",icon:"el-icon-search"},on:{click:t.search}},[t._v("查询")]),n("el-button",{staticClass:"right-item",attrs:{type:"primary",size:"medium",icon:"el-icon-upload"},on:{click:function(e){t.isDrawerShow=!t.isDrawerShow}}},[t._v("上传")])],1),t.hintShow1?n("el-alert",{attrs:{title:"只显示<img>标签支持的 apng,avif,bmp,gif,ico,cur,jpg,jpeg,jfif,pjpeg,pjp,png,svg,tif,tiff,webp 格式的图片，见 https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/img",type:"warning","show-icon":"","close-text":"不再提示"},on:{close:function(e){return t.noDisplay(1)}}}):t._e(),t.hintShow2?n("el-alert",{attrs:{title:"最多显示100个文件",type:"warning","show-icon":"","close-text":"不再提示"},on:{close:function(e){return t.noDisplay(2)}}}):t._e(),n("el-row",{directives:[{name:"viewer",rawName:"v-viewer"}]},t._l(t.fileList,(function(e,a){return n("div",{key:a,staticClass:"image-container"},[n("el-image",{attrs:{src:e.cdn_url,fit:"scale-down"}}),n("div",{staticClass:"image-content"},[n("div",{staticClass:"info"},[n("span",[t._v(t._s(e.name))])]),n("div",{staticClass:"icons"},[n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"复制CDN链接",placement:"bottom"}},[n("i",{staticClass:"icon el-icon-link",on:{click:function(n){return t.copy(1,e)}}})]),n("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"复制MD格式",placement:"bottom"}},[n("SvgIcon",{attrs:{"icon-class":"markdown","class-name":"icon"},on:{click:function(n){return t.copy(2,e)}}})],1),n("i",{staticClass:"icon el-icon-delete",on:{click:function(n){return t.delFile(e)}}})],1)])],1)})),0),n("el-drawer",{attrs:{title:"上传文件",visible:t.isDrawerShow,direction:"rtl",size:"40%",wrapperClosable:!1,"close-on-press-escape":!1},on:{"update:visible":function(e){t.isDrawerShow=e}}},[n("el-row",[n("el-radio",{attrs:{label:"1"},model:{value:t.nameType,callback:function(e){t.nameType=e},expression:"nameType"}},[t._v("使用源文件名")]),n("el-radio",{attrs:{label:"2"},model:{value:t.nameType,callback:function(e){t.nameType=e},expression:"nameType"}},[t._v("使用UUID文件名")]),n("el-button",{directives:[{name:"throttle",rawName:"v-throttle",value:[t.submitUpload,"click",3e3],expression:"[submitUpload,`click`,3000]"}],attrs:{size:"small",type:"primary",icon:"el-icon-upload"}},[t._v("确定上传")])],1),n("el-row",[t._v(" 当前目录："+t._s(t.realPath)+" ")]),n("el-row",[n("el-switch",{attrs:{"active-text":"自定义目录"},model:{value:t.isCustomPath,callback:function(e){t.isCustomPath=e},expression:"isCustomPath"}}),n("el-input",{staticStyle:{"margin-top":"10px"},attrs:{placeholder:"例：oldFolder/newFolder/",disabled:!t.isCustomPath,size:"medium"},model:{value:t.customPath,callback:function(e){t.customPath=e},expression:"customPath"}})],1),n("el-upload",{ref:"uploadRef",attrs:{action:"","http-request":t.upload,drag:"",multiple:"","file-list":t.uploadList,"list-type":"picture","auto-upload":!1}},[n("i",{staticClass:"el-icon-upload"}),n("div",{staticClass:"el-upload__text"},[t._v("将文件拖到此处，或"),n("em",[t._v("点击上传")])])])],1)],1)},i=[],o=n("1da1"),c=(n("96cf"),n("a15b"),n("d3b7"),n("159b"),n("b0c0"),n("2ca0"),n("8a79"),n("99af"),n("ac1f"),n("841c"),n("c00a")),s=n("bc3a"),r=n.n(s),l=n("323e"),u=n.n(l),p=(n("a5d8"),n("5c96")),h=r.a.create({baseURL:"https://v0.api.upyun.com",timeout:3e4});function f(t,e){return e=e.startsWith("/")?e:"/".concat(e),h({url:"/".concat(t).concat(e),method:"GET"})}function m(t,e){return h({url:"/".concat(t).concat(e),method:"DELETE"})}function d(t,e,n,a){return e=e.startsWith("/")?e:"/".concat(e),e=e.endsWith("/")?e:"".concat(e,"/"),h({url:"/".concat(t).concat(e).concat(n),method:"PUT",data:a})}h.interceptors.request.use((function(t){u.a.start(),t.headers["Accept"]="application/json";var e=localStorage.getItem("upyunToken");return e&&(t.headers["Authorization"]="Basic ".concat(e)),t}),(function(t){return console.info(t),Promise.reject(t)})),h.interceptors.response.use((function(t){return u.a.done(),t.data}),(function(t){return console.info(t),p["Message"].error(t.message),Promise.reject(t)}));var g=n("c54a"),v=n("f988"),b=n("f43e"),w={name:"UpyunManage",components:{SvgIcon:c["a"]},data:function(){var t=this;return{upyunConfig:{username:"",password:"",bucketName:"",domain:""},pathArr:[{value:"",label:"根目录"}],activePath:[""],pathProps:{lazy:!0,checkStrictly:!0,lazyLoad:function(){var e=Object(o["a"])(regeneratorRuntime.mark((function e(n,a){var i,o;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return i=n.path.join("/"),o=[],e.next=4,t.getReposContents(o,i);case 4:a(o);case 5:case"end":return e.stop()}}),e)})));function n(t,n){return e.apply(this,arguments)}return n}()},hintShow1:!0,hintShow2:!0,fileList:[],isDrawerShow:!1,nameType:"1",uploadList:[],isCustomPath:!1,customPath:""}},computed:{realPath:function(){return this.isCustomPath?"/".concat(this.customPath):"".concat(this.activePath.join("/"),"/")}},created:function(){this.hintShow1=!localStorage.getItem("upyunHintShow1"),this.hintShow2=!localStorage.getItem("upyunHintShow2");var t=localStorage.getItem("upyunConfig");t?this.upyunConfig=JSON.parse(t):(this.msgError("请先配置又拍云"),this.$router.push("/pictureHosting/setting"))},methods:{getReposContents:function(t,e){var n=this;return Object(o["a"])(regeneratorRuntime.mark((function a(){return regeneratorRuntime.wrap((function(a){while(1)switch(a.prev=a.next){case 0:return a.next=2,f(n.upyunConfig.bucketName,e).then((function(e){e.files.forEach((function(e){"folder"===e.type&&t.push({value:e.name,label:e.name,leaf:!1})}))}));case 2:case"end":return a.stop()}}),a)})))()},search:function(){var t=this;this.fileList=[];var e=this.activePath.join("/");f(this.upyunConfig.bucketName,e).then((function(e){e.files.forEach((function(e){"folder"!==e.type&&Object(g["b"])(e.name)&&(e.path=t.imgPath(e),e.cdn_url=t.imgCdnUrl(e),t.fileList.push(e))}))}))},noDisplay:function(t){localStorage.setItem("upyunHintShow".concat(t),"1")},imgPath:function(t){var e=this.activePath.join("/");return e=e.startsWith("/")?e:"/".concat(e),e=e.endsWith("/")?e:"".concat(e,"/"),"".concat(e).concat(t.name)},imgCdnUrl:function(t){return"".concat(this.upyunConfig.domain).concat(this.imgPath(t))},copy:function(t,e){var n="";1==t?n=e.cdn_url:2==t&&(n="![".concat(e.name,"](").concat(e.cdn_url,")")),Object(b["a"])(n),this.msgSuccess("复制成功")},delFile:function(t){var e=this;this.$confirm("此操作将永久删除该文件, 是否删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){m(e.upyunConfig.bucketName,t.path).then((function(){e.msgSuccess("删除成功"),e.search()}))})).catch((function(){e.$message({type:"info",message:"已取消删除"})}))},submitUpload:function(){this.uploadList=this.$refs.uploadRef.uploadFiles,this.uploadList.length?this.$refs.uploadRef.submit():this.msgError("请先选择文件")},upload:function(t){var e=this,n=t.file.name;"2"===this.nameType&&(n=Object(v["a"])()+n.substr(n.lastIndexOf("."))),d(this.upyunConfig.bucketName,this.realPath,n,t.file).then((function(){e.msgSuccess("上传成功"),t.onSuccess()}))}}},y=w,S=(n("8788"),n("19cb"),n("2877")),C=Object(S["a"])(y,a,i,!1,null,"9a1f671a",null);e["default"]=C.exports},8788:function(t,e,n){"use strict";n("5b9d")},"8a79":function(t,e,n){"use strict";var a=n("23e7"),i=n("06cf").f,o=n("50c4"),c=n("5a34"),s=n("1d80"),r=n("ab13"),l=n("c430"),u="".endsWith,p=Math.min,h=r("endsWith"),f=!l&&!h&&!!function(){var t=i(String.prototype,"endsWith");return t&&!t.writable}();a({target:"String",proto:!0,forced:!f&&!h},{endsWith:function(t){var e=String(s(this));c(t);var n=arguments.length>1?arguments[1]:void 0,a=o(e.length),i=void 0===n?a:p(o(n),a),r=String(t);return u?u.call(e,r,i):e.slice(i-r.length,i)===r}})}}]);