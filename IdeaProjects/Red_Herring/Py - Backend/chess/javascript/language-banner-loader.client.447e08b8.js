!function(e){var t={};function __webpack_require__(n){if(t[n])return t[n].exports;var r=t[n]={i:n,l:!1,exports:{}};return e[n].call(r.exports,r,r.exports,__webpack_require__),r.l=!0,r.exports}__webpack_require__.m=e,__webpack_require__.c=t,__webpack_require__.d=function(e,t,n){__webpack_require__.o(e,t)||Object.defineProperty(e,t,{configurable:!1,enumerable:!0,get:n})},__webpack_require__.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return __webpack_require__.d(t,"a",t),t},__webpack_require__.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},__webpack_require__.p="",__webpack_require__(__webpack_require__.s=1996)}({11:function(e,t,n){e.exports=n(6)(45)},1114:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0}),t.refs=t.routes=void 0;var r=n(44);t.routes={userHome:(0,r.generateRoute)("web_user_home"),languageSet:function(e,t){return(0,r.generateRoute)("web_language_set",{locale:e,returnUrl:t})},languageI18n:function(e,t){return(0,r.generateRoute)("web_language_i18n",{locale:e,url:t})}},t.refs={LANGUAGE_BANNER:"language-banner"}},1406:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0}),t.getLocaleDataFromServer=function(){var e=o.routes.languageI18n((0,a.getLocale)("","_"),window.location.pathname),t={random:(Math.random().toString(36)+"00000000000000000").slice(2,14)};return i.default.get(e,t)};var r,a=n(30),o=n(1114),s=n(27),i=(r=s)&&r.__esModule?r:{default:r}},1996:function(e,t,n){e.exports=n(1997)},1997:function(e,t,n){var r=n(784),a=n(30),o=n(1406),s=(0,r.getCookie)("cclocale"),i=(0,a.getLocale)("","_"),u=localStorage.getItem("i18n_routing_should_hide_banner"),c=function(){var e=document.head.querySelector('[name="language-banner-scripts"]'),t=e&&e.getAttribute("content"),n=document.createElement("script");n.setAttribute("src",t),document.body.appendChild(n)};i===s||u||document.addEventListener("DOMContentLoaded",function(){(0,o.getLocaleDataFromServer)().then(function(e){var t,n,o;204!==e.status?(window.chessComLanguageBannerData=e,(t=document.head.querySelector('[name="language-banner-styles"]'),n=t&&t.getAttribute("content"),(o=document.createElement("link")).setAttribute("rel","stylesheet"),o.setAttribute("type","text/css"),o.setAttribute("href",n),document.head.appendChild(o),o).onload=c):(0,r.setCookie)("cclocale",(0,a.getLocale)("","_"),365)})})},21:function(e,t,n){e.exports=n(6)(58)},219:function(e,t,n){e.exports={default:n(255),__esModule:!0}},224:function(e,t,n){e.exports=n(6)(106)},225:function(e,t,n){e.exports=n(6)(33)},23:function(e,t,n){e.exports=n(6)(59)},255:function(e,t,n){n(224),n(225),e.exports=n(256)},256:function(e,t,n){var r=n(257),a=n(258);e.exports=n(92).getIterator=function(e){var t=a(e);if("function"!=typeof t)throw TypeError(e+" is not iterable!");return r(t.call(e))}},257:function(e,t,n){e.exports=n(6)(7)},258:function(e,t,n){e.exports=n(6)(55)},27:function(e,t,n){e.exports=n(6)(60)},28:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0}),t.Translator=t.getCountries=t.hasTranslation=t.transChoice=t.trans=t.getTranslatedPhrase=void 0;var r=_interopRequireDefault(n(11)),a=_interopRequireDefault(n(87));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}a.default.placeHolderPrefix="",a.default.placeHolderSuffix="";var o=function(e){var t={};return null!==e&&void 0!==e&&(0,r.default)(e).forEach(function(n){var r=n.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g,"\\$&");null!=r&&r.length>0&&(t[r]=e[n])}),t},s=t.getTranslatedPhrase=function getTranslatedPhrase(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"messages",n=arguments[2];return window.chesscom_translations&&window.chesscom_translations[t]&&window.chesscom_translations[t][e]?window.chesscom_translations[t][e]:window.chesscom_live_translations&&window.chesscom_live_translations[t]&&window.chesscom_live_translations[t][e]?window.chesscom_live_translations[t][e]:"live"===t&&window.i18n_phrases&&window.i18n_phrases[e]?window.i18n_phrases:"countries"===t&&window.Country&&window.Country[e]?window.Country:n?void 0:"javascript"!==t?getTranslatedPhrase(e,"javascript"):e},i=t.trans=function(e,t,n){return a.default.trans(s(e,n),o(t),n)},u=t.transChoice=function(e,t,n,r){return void 0===t?"":a.default.transChoice(s(e,r),t,o(n))};t.hasTranslation=function(e,t){return!!s(e,t,!0)},t.getCountries=function(){return window.chesscom_translations&&window.chesscom_translations.countries?window.chesscom_translations.countries:window.chesscom_live_translations&&window.chesscom_live_translations.countries?window.chesscom_live_translations.countries:window.Country?window.Country:void 0},t.Translator={trans:i,transChoice:u}},30:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0}),t.formatDate=void 0,t.getLocale=getLocale,t.formatNumber=formatNumber,t.formatTime=formatTime;var r,a=n(7),o=(r=a)&&r.__esModule?r:{default:r};function getLocale(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"-";return(e||(Object.prototype.hasOwnProperty.call(window,"context")?window.context.i18n.locale:"en-US")).replace("_",t)}var s={year:{milliseconds:31536e6,str:"{1} 1 year|]1,Inf] %1$s% years",strAgo:"{1} 1 year ago|]1,Inf] %1$s% years ago"},month:{milliseconds:function(){switch((new Date).getMonth()){case 3:case 5:case 8:case 10:return 2592e6;case 1:return(new Date).getFullYear()%4==0?25056e5:24192e5;default:return 26784e5}}(),str:"{1} 1 month|]1,Inf] %1$s% months",strAgo:"{1} 1 month ago|]1,Inf] %1$s% months ago"},day:{milliseconds:864e5,str:"{1} 1 day|]1,Inf] %1$s% days",strAgo:"{1} 1 day ago|]1,Inf] %1$s% days ago"},hour:{milliseconds:36e5,str:"{1} 1 hour|]1,Inf] %1$s% hours",strAgo:"{1} 1 hour ago|]1,Inf] %1$s% hours ago"},minute:{milliseconds:6e4,str:"{1} 1 minute|]1,Inf] %1$s% minutes",strAgo:"{1} 1 minute ago|]1,Inf] %1$s% minutes ago"}},i=t.formatDate={hoursMinute:function(e){var t,n,r=[],a=0,s=e;return!0===(arguments.length>1&&void 0!==arguments[1]&&arguments[1])&&(s-=86400*(a=Math.floor(s/86400))),s-=3600*(t=Math.floor(s/3600)),n=Math.floor(s/60),a>0&&r.push(o.default.transChoice("{0} 0 days|{1} 1 day|]1,Inf] %1$s% days",a,{"%1$s%":a})),t>0&&r.push(o.default.transChoice("{0} 0 hours|{1} 1 hour|]1,Inf] %1$s% hours",t,{"%1$s%":t})),(n>0||0===r.length)&&r.push(o.default.transChoice("{0} 0 min|{1} 1 min|]1,Inf] %1$s% min",n,{"%1$s%":n})),r.join(" ")},long:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,t=e instanceof Date?e:new Date(e);return new Intl.DateTimeFormat(getLocale(),{year:"numeric",month:"short",day:"numeric"}).format(t)},full:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,t=e instanceof Date?e:new Date(e);return new Intl.DateTimeFormat(getLocale(),{year:"numeric",month:"short",day:"numeric",hour:"numeric",minute:"numeric",second:"numeric",timeZoneName:"short"}).format(t)},numeric:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,t=e instanceof Date?e:new Date(e);if(window.Intl){var n=getLocale();return new Intl.DateTimeFormat(n,{day:"2-digit",month:"2-digit",year:"numeric"}).format(t)}return("0"+(t.getMonth()+1)).slice(-2)+"/"+("0"+t.getDate()).slice(-2)+"/"+t.getFullYear()},relative:function(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1],n=!(arguments.length>2&&void 0!==arguments[2])||arguments[2],r=void 0,a=void 0,i=Date.now(),u=(e instanceof Date?e:new Date(e)).getTime(),c=i-u;if(Math.abs(c)>s.month.milliseconds&&t){return new Intl.DateTimeFormat(getLocale(),{year:"numeric",month:"short",day:"numeric"}).format(u)}if(c>=s.year.milliseconds)a=s.year,r=Math.abs(Math.floor(c/s.year.milliseconds));else if(c>=s.month.milliseconds)a=s.month,r=Math.abs(Math.floor(c/s.month.milliseconds));else if(c>=s.day.milliseconds)a=s.day,r=Math.abs(Math.floor(c/s.day.milliseconds));else if(c>=s.hour.milliseconds)a=s.hour,r=Math.abs(Math.floor(c/s.hour.milliseconds));else{if(!(c>=s.minute.milliseconds))return o.default.trans("Just now");a=s.minute,r=Math.abs(Math.floor(c/s.minute.milliseconds))}var l=Math.abs(c)===c;return o.default.transChoice(l&&n?a.strAgo:a.str,r,{"%1$s%":r})},customNumericDate:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:new Date,t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"/",n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"m/d/y",r=e instanceof Date?e:new Date(e),a={d:("0"+r.getDate()).slice(-2),m:("0"+(r.getMonth()+1)).slice(-2),y:""+r.getFullYear()};return["m","d","y"].every(function(e){return n.split("/").includes(e)})?n.split("/").reduce(function(e,t){return e.push(a[t]),e},[]).join(t):""+a.m+t+a.d+t+a.y}};function formatNumber(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"",n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:{};return new Intl.NumberFormat([getLocale(t),"en-US"],n).format(e)}function formatTime(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0;if(e<0||null==e)return"";var n=(e%1e3/1e3).toString().slice(2,t+2),r=Math.floor(e/1e3),a=Math.floor(r/60),o=Math.floor(a/60),s=a%60,i=r%60,u=(s=s<10&&o?"0"+s:s)+":"+(i=i<10?"0"+i:i);return o&&(u=o+":"+u),t&&(u=u+"."+n),u}t.default={formatDate:i,getLocale:getLocale,formatNumber:formatNumber,formatTime:formatTime}},44:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0}),t.generateRoute=void 0;var r,a=n(8),o=(r=a)&&r.__esModule?r:{default:r};t.generateRoute=function(e,t,n){var r=e,a=t;if(n){var s=document.querySelector("[data-url-locale]"),i=s&&s.getAttribute("data-url-locale"),u=i&&"en"!==i.split("_")[0]&&i.split("_")[0];u&&a&&(r="i18n_"+r,a._locale=u)}return o.default.generate(r,a)}},479:function(e,t,n){"use strict";t.__esModule=!0;var r=_interopRequireDefault(n(612)),a=_interopRequireDefault(n(219));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}t.default=function(){return function(e,t){if(Array.isArray(e))return e;if((0,r.default)(Object(e)))return function(e,t){var n=[],r=!0,o=!1,s=void 0;try{for(var i,u=(0,a.default)(e);!(r=(i=u.next()).done)&&(n.push(i.value),!t||n.length!==t);r=!0);}catch(e){o=!0,s=e}finally{try{!r&&u.return&&u.return()}finally{if(o)throw s}}return n}(e,t);throw new TypeError("Invalid attempt to destructure non-iterable instance")}}()},6:function(e,t){e.exports=vueDLL},612:function(e,t,n){e.exports={default:n(613),__esModule:!0}},613:function(e,t,n){n(224),n(225),e.exports=n(614)},614:function(e,t,n){var r=n(615),a=n(616)("iterator"),o=n(617);e.exports=n(92).isIterable=function(e){var t=Object(e);return void 0!==t[a]||"@@iterator"in t||o.hasOwnProperty(r(t))}},615:function(e,t,n){e.exports=n(6)(56)},616:function(e,t,n){e.exports=n(6)(2)},617:function(e,t,n){e.exports=n(6)(13)},7:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0});var r=n(28);t.default=r.Translator},784:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0});var r,a=n(479),o=(r=a)&&r.__esModule?r:{default:r};function setCookie(e,t,n){var r="";if(n){var a=new Date;a.setTime(a.getTime()+24*n*60*60*1e3),r="; expires="+a.toUTCString()}document.cookie=e+"="+(t||"")+r+"; path=/"}function deleteCookie(e){setCookie(e,"",-3650)}function getCookie(e){var t={};return document.cookie.split("; ").forEach(function(e){var n=e.split("="),r=(0,o.default)(n,2),a=r[0],s=r[1];a&&s&&(t[a]=s)}),t[e]||null}t.setCookie=setCookie,t.deleteCookie=deleteCookie,t.getCookie=getCookie,t.default={getCookie:getCookie,deleteCookie:deleteCookie,setCookie:setCookie}},8:function(e,t,n){Object.defineProperty(t,"__esModule",{value:!0});var r=_interopRequireDefault(n(21)),a=_interopRequireDefault(n(23));function _interopRequireDefault(e){return e&&e.__esModule?e:{default:e}}var o=function(){function Router(){(0,r.default)(this,Router),this.baseUrl=null,null==window.Routing&&(window.Routing={generate:function(e){return e}})}return(0,a.default)(Router,[{key:"generate",value:function(e,t,n){var r=t||{},a=this.baseUrl||r.baseUrl||null;return"string"==typeof r.username&&(r.username=r.username.toLowerCase()),null!=a?(delete r.baseUrl,a+window.Routing.generate(e,r,!1)):window.Routing.generate(e,r,n)}},{key:"setBaseUrl",value:function(e){this.baseUrl=e}}]),Router}();t.default=new o},87:function(e,t,n){var r,a;
/**
 * @author William DURAND <william.durand1@gmail.com>
 * @license MIT Licensed
 */void 0===(a="function"==typeof(r=function(){"use strict";var e={},t="en",n=[],r=new RegExp(/^\w+\: +(.+)$/),a=new RegExp(/^\s*((\{\s*(\-?\d+[\s*,\s*\-?\d+]*)\s*\})|([\[\]])\s*(-Inf|\-?\d+)\s*,\s*(\+?Inf|\-?\d+)\s*([\[\]]))\s?(.+?)$/),o=new RegExp(/^\s*(\{\s*(\-?\d+[\s*,\s*\-?\d+]*)\s*\})|([\[\]])\s*(-Inf|\-?\d+)\s*,\s*(\+?Inf|\-?\d+)\s*([\[\]])/),s={locale:get_current_locale(),fallback:t,placeHolderPrefix:"%",placeHolderSuffix:"%",defaultDomain:"messages",pluralSeparator:"|",add:function(t,r,a,o){var s=o||this.locale||this.fallback,i=a||this.defaultDomain;return e[s]||(e[s]={}),e[s][i]||(e[s][i]={}),e[s][i][t]=r,!1===function(e,t){for(var n=0;n<e.length;n++)if(t===e[n])return!0;return!1}(n,i)&&n.push(i),this},trans:function(e,t,n,r){return replace_placeholders(get_message(e,n,r,this.locale,this.fallback),t||{})},transChoice:function(e,t,n,i,u){var c=get_message(e,i,u,this.locale,this.fallback),l=parseInt(t,10);return void 0===(n=n||{}).count&&(n.count=t),void 0===c||isNaN(l)||(c=function(e,t,n){var i,u,c=[],l=[],f=e.split(s.pluralSeparator),d=[];for(i=0;i<f.length;i++){var h=f[i];a.test(h)?(d=h.match(a),c[d[0]]=d[d.length-1]):r.test(h)?(d=h.match(r),l.push(d[1])):l.push(h)}for(u in c)if(o.test(u))if((d=u.match(o))[1]){var m,_=d[2].split(",");for(m in _)if(t==_[m])return c[u]}else{var g=convert_number(d[4]),p=convert_number(d[5]);if(("["===d[3]?t>=g:t>g)&&("]"===d[6]?t<=p:t<p))return c[u]}return l[function(e,t){var n=t;"pt_BR"===n&&(n="xbr");n.length>3&&(n=n.split("_")[0]);switch(n){case"bo":case"dz":case"id":case"ja":case"jv":case"ka":case"km":case"kn":case"ko":case"ms":case"th":case"tr":case"vi":case"zh":return 0;case"af":case"az":case"bn":case"bg":case"ca":case"da":case"de":case"el":case"en":case"eo":case"es":case"et":case"eu":case"fa":case"fi":case"fo":case"fur":case"fy":case"gl":case"gu":case"ha":case"he":case"hu":case"is":case"it":case"ku":case"lb":case"ml":case"mn":case"mr":case"nah":case"nb":case"ne":case"nl":case"nn":case"no":case"om":case"or":case"pa":case"pap":case"ps":case"pt":case"so":case"sq":case"sv":case"sw":case"ta":case"te":case"tk":case"ur":case"zu":return 1==e?0:1;case"am":case"bh":case"fil":case"fr":case"gun":case"hi":case"ln":case"mg":case"nso":case"xbr":case"ti":case"wa":return 0===e||1==e?0:1;case"be":case"bs":case"hr":case"ru":case"sr":case"uk":return e%10==1&&e%100!=11?0:e%10>=2&&e%10<=4&&(e%100<10||e%100>=20)?1:2;case"cs":case"sk":return 1==e?0:e>=2&&e<=4?1:2;case"ga":return 1==e?0:2==e?1:2;case"lt":return e%10==1&&e%100!=11?0:e%10>=2&&(e%100<10||e%100>=20)?1:2;case"sl":return e%100==1?0:e%100==2?1:e%100==3||e%100==4?2:3;case"mk":return e%10==1?0:1;case"mt":return 1==e?0:0===e||e%100>1&&e%100<11?1:e%100>10&&e%100<20?2:3;case"lv":return 0===e?0:e%10==1&&e%100!=11?1:2;case"pl":return 1==e?0:e%10>=2&&e%10<=4&&(e%100<12||e%100>14)?1:2;case"cy":return 1==e?0:2==e?1:8==e||11==e?2:3;case"ro":return 1==e?0:0===e||e%100>0&&e%100<20?1:2;case"ar":return 0===e?0:1==e?1:2==e?2:e>=3&&e<=10?3:e>=11&&e<=99?4:5;default:return 0}}(t,n)]||l[0]||void 0}(c,l,u||this.locale||this.fallback)),replace_placeholders(c,n)},fromJSON:function(e){if("string"==typeof e&&(e=JSON.parse(e)),e.locale&&(this.locale=e.locale),e.fallback&&(this.fallback=e.fallback),e.defaultDomain&&(this.defaultDomain=e.defaultDomain),e.translations)for(var t in e.translations)for(var n in e.translations[t])for(var r in e.translations[t][n])this.add(r,e.translations[t][n][r],n,t);return this},reset:function(){e={},n=[],this.locale=get_current_locale()}};function replace_placeholders(e,t){var n,r=s.placeHolderPrefix,a=s.placeHolderSuffix;for(n in t){var o=new RegExp(r+n+a,"g");if(o.test(e)){var i=String(t[n]).replace(new RegExp("\\$","g"),"$$$$");e=e.replace(o,i)}}return e}function get_message(t,r,a,o,s){var i,u,c,l=a||o||s,f=r,d=l.split("_")[0];if(!(l in e))if(d in e)l=d;else{if(!(s in e))return t;l=s}if(void 0===f||null===f)for(var h=0;h<n.length;h++)if(has_message(l,n[h],t)||has_message(d,n[h],t)||has_message(s,n[h],t)){f=n[h];break}if(has_message(l,f,t))return e[l][f][t];for(;l.length>2&&(i=l.length,c=(u=l.split(/[\s_]+/))[u.length-1].length,1!==u.length);)if(has_message(l=l.substring(0,i-(c+1)),f,t))return e[l][f][t];return has_message(s,f,t)?e[s][f][t]:t}function has_message(t,n,r){return t in e&&(n in e[t]&&r in e[t][n])}function convert_number(e){return"-Inf"===e?Number.NEGATIVE_INFINITY:"+Inf"===e||"Inf"===e?Number.POSITIVE_INFINITY:parseInt(e,10)}function get_current_locale(){return"undefined"!=typeof document?document.documentElement.lang.replace("-","_"):t}return s})?r.call(t,n,t,e):r)||(e.exports=a)},92:function(e,t,n){e.exports=n(6)(1)}});