(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5a57a8b8"],{4020:function(e,t,r){"use strict";r("d3b7");var o=r("bc3a"),n=r.n(o),a=r("323e"),c=r.n(a),i=(r("a5d8"),r("5c96")),s=n.a.create({baseURL:"http://81.68.124.95:8090/admin/",timeout:5e3}),l=n.a.CancelToken;s.interceptors.request.use((function(e){var t=window.localStorage.getItem("user")||"{}",r=JSON.parse(t);if("{}"!==t&&"ROLE_admin"!==r.role&&"get"!==e.method)return e.cancelToken=new l((function(e){e("演示模式，不允许操作")})),e;c.a.start();var o=window.localStorage.getItem("token");return o&&(e.headers.Authorization=o),e}),(function(e){return console.info(e),Promise.reject(e)})),s.interceptors.response.use((function(e){c.a.done();var t=e.data;if(200!==t.code){var r=t.msg||"Error";return i["Message"].error(r),Promise.reject(new Error(r))}return t}),(function(e){return console.info(e),i["Message"].error(e.message),Promise.reject(e)})),t["a"]=s},e3eb:function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("el-form",{ref:"formRef",attrs:{model:e.form,rules:e.formRules,"label-position":"top"}},[r("el-form-item",{staticStyle:{width:"50%"},attrs:{label:"标题",prop:"title"}},[r("el-input",{attrs:{placeholder:"请输入标题"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),r("el-row",{staticStyle:{width:"50%"},attrs:{gutter:20}},[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"网易云歌曲ID",prop:"musicId"}},[r("el-input",{attrs:{type:"number",placeholder:"请输入网易云歌曲ID（可选）"},model:{value:e.form.musicId,callback:function(t){e.$set(e.form,"musicId",t)},expression:"form.musicId"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"评论开关"}},[r("el-switch",{attrs:{"active-text":"评论"},model:{value:e.form.commentEnabled,callback:function(t){e.$set(e.form,"commentEnabled",t)},expression:"form.commentEnabled"}})],1)],1)],1),r("el-form-item",{attrs:{label:"正文",prop:"content"}},[r("mavon-editor",{model:{value:e.form.content,callback:function(t){e.$set(e.form,"content",t)},expression:"form.content"}})],1),r("el-form-item",{staticStyle:{"text-align":"right"}},[r("el-button",{attrs:{type:"primary",icon:"el-icon-check"},on:{click:e.submit}},[e._v("保存")])],1)],1)],1)},n=[],a=(r("ac1f"),r("00b4"),r("6350")),c=r("5530"),i=r("4020");function s(){return Object(i["a"])({url:"about",method:"GET"})}function l(e){return Object(i["a"])({url:"about",method:"PUT",data:Object(c["a"])({},e)})}var m={name:"About",components:{Breadcrumb:a["a"]},data:function(){return{form:{title:"",musicId:null,content:"",commentEnabled:!0},formRules:{title:[{required:!0,message:"请输入标题",trigger:"change"}]}}},created:function(){this.getData()},methods:{getData:function(){var e=this;s().then((function(t){e.form.title=t.data.title,e.form.musicId=t.data.musicId,e.form.content=t.data.content,e.form.commentEnabled="true"===t.data.commentEnabled}))},submit:function(){var e=this;this.$refs.formRef.validate((function(t){if(!t)return e.msgError("请填写必要的表单");var r=/^\d{1,}$/;if(!r.test(e.form.musicId))return e.msgError("歌曲ID有误");l(e.form).then((function(t){e.msgSuccess(t.msg)}))}))}}},u=m,f=r("2877"),d=Object(f["a"])(u,o,n,!1,null,"76caae6d",null);t["default"]=d.exports}}]);