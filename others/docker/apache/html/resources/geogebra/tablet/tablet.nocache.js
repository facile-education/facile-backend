function tablet(){var O='bootstrap',P='begin',Q='gwt.codesvr.tablet=',R='gwt.codesvr=',S='tablet',T='startup',U='DUMMY',V=0,W=1,X='iframe',Y='javascript:""',Z='position:absolute; width:0; height:0; border:none; left: -1000px;',$=' top: -1000px;',_='CSS1Compat',ab='<!doctype html>',bb='',cb='<html><head><\/head><body><\/body><\/html>',db='undefined',eb='DOMContentLoaded',fb=50,gb='Chrome',hb='eval("',ib='");',jb='script',kb='javascript',lb='moduleStartup',mb='moduleRequested',nb='Failed to load ',ob='head',pb='meta',qb='name',rb='tablet::',sb='::',tb='gwt:property',ub='content',vb='=',wb='gwt:onPropertyErrorFn',xb='Bad handler "',yb='" for "gwt:onPropertyErrorFn"',zb='gwt:onLoadErrorFn',Ab='" for "gwt:onLoadErrorFn"',Bb='#',Cb='?',Db='/',Eb='img',Fb='clear.cache.gif',Gb='baseUrl',Hb='tablet.nocache.js',Ib='base',Jb='//',Kb='phonegap.env',Lb='android',Mb='ipad',Nb='ipod',Ob='iphone',Pb='blackberry',Qb='file://',Rb='local://',Sb='yes',Tb='no',Ub='user.agent',Vb='webkit',Wb='safari',Xb='msie',Yb=10,Zb=11,$b='ie10',_b=9,ac='ie9',bc=8,cc='ie8',dc='gecko',ec='gecko1_8',fc=2,gc=3,hc=4,ic='selectingPermutation',jc='tablet.devmode.js',kc='1B9934BA9918C42D664C9A82C15B468C',lc=':1',mc=':2',nc=':3',oc=':4',pc=':',qc='.cache.js',rc='loadExternalRefs',sc='end',tc='http:',uc='file:',vc='_gwt_dummy_',wc='__gwtDevModeHook:tablet',xc='Ignoring non-whitelisted Dev Mode URL: ',yc=':moduleBase';var o=window;var p=document;r(O,P);function q(){var a=o.location.search;return a.indexOf(Q)!=-1||a.indexOf(R)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:S,sessionId:o.__gwtStatsSessionId,subSystem:T,evtGroup:a,millis:(new Date).getTime(),type:b})}}
tablet.__sendStats=r;tablet.__moduleName=S;tablet.__errFn=null;tablet.__moduleBase=U;tablet.__softPermutationId=V;tablet.__computePropValue=null;tablet.__getPropMap=null;tablet.__installRunAsyncCode=function(){};tablet.__gwtStartLoadingFragment=function(){return null};tablet.__gwt_isKnownPropertyValue=function(){return false};tablet.__gwt_getMetaProperty=function(){return null};var s=null;var t=o.__gwt_activeModules=o.__gwt_activeModules||{};t[S]={moduleName:S};tablet.__moduleStartupDone=function(e){var f=t[S].bindings;t[S].bindings=function(){var a=f?f():{};var b=e[tablet.__softPermutationId];for(var c=V;c<b.length;c++){var d=b[c];a[d[V]]=d[W]}return a}};var u;function v(){w();return u}
function w(){if(u){return}var a=p.createElement(X);a.src=Y;a.id=S;a.style.cssText=Z+$;a.tabIndex=-1;p.body.appendChild(a);u=a.contentDocument;if(!u){u=a.contentWindow.document}u.open();var b=document.compatMode==_?ab:bb;u.write(b+cb);u.close()}
function A(k){function l(a){function b(){if(typeof p.readyState==db){return typeof p.body!=db&&p.body!=null}return /loaded|complete/.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(eb,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(eb,d,false)}var e=setInterval(function(){if(b()){d()}},fb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=v();var f=e.body;var g;if(navigator.userAgent.indexOf(gb)>-1&&window.JSON){var h=e.createDocumentFragment();h.appendChild(e.createTextNode(hb));for(var i=V;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(e.createTextNode(j.substring(W,j.length-W)))}h.appendChild(e.createTextNode(ib));g=e.createElement(jb);g.language=kb;g.appendChild(h);f.appendChild(g);d(f,g)}else{for(var i=V;i<c.length;i++){g=e.createElement(jb);g.language=kb;g.text=c[i];f.appendChild(g);d(f,g)}}}
tablet.onScriptDownloaded=function(a){l(function(){m(a)})};r(lb,mb);var n=p.createElement(jb);n.src=k;if(tablet.__errFn){n.onerror=function(){tablet.__errFn(S,new Error(nb+code))}}p.getElementsByTagName(ob)[V].appendChild(n)}
tablet.__startLoadingFragment=function(a){return D(a)};tablet.__installRunAsyncCode=function(a){var b=v();var c=b.body;var d=b.createElement(jb);d.language=kb;d.text=a;c.appendChild(d);c.removeChild(d)};function B(){var c={};var d;var e;var f=p.getElementsByTagName(pb);for(var g=V,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(qb),k;if(j){j=j.replace(rb,bb);if(j.indexOf(sb)>=V){continue}if(j==tb){k=i.getAttribute(ub);if(k){var l,m=k.indexOf(vb);if(m>=V){j=k.substring(V,m);l=k.substring(m+W)}else{j=k;l=bb}c[j]=l}}else if(j==wb){k=i.getAttribute(ub);if(k){try{d=eval(k)}catch(a){alert(xb+k+yb)}}}else if(j==zb){k=i.getAttribute(ub);if(k){try{e=eval(k)}catch(a){alert(xb+k+Ab)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};s=d;tablet.__errFn=e}
function C(){function e(a){var b=a.lastIndexOf(Bb);if(b==-1){b=a.length}var c=a.indexOf(Cb);if(c==-1){c=a.length}var d=a.lastIndexOf(Db,Math.min(c,b));return d>=V?a.substring(V,d+W):bb}
function f(a){if(a.match(/^\w+:\/\//)){}else{var b=p.createElement(Eb);b.src=a+Fb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Gb);if(a!=null){return a}return bb}
function h(){var a=p.getElementsByTagName(jb);for(var b=V;b<a.length;++b){if(a[b].src.indexOf(Hb)!=-1){return e(a[b].src)}}return bb}
function i(){var a=p.getElementsByTagName(Ib);if(a.length>V){return a[a.length-W].href}return bb}
function j(){var a=p.location;return a.href==a.protocol+Jb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==bb){k=h()}if(k==bb){k=i()}if(k==bb&&j()){k=e(p.location.href)}k=f(k);return k}
function D(a){if(a.match(/^\//)){return a}if(a.match(/^[a-zA-Z]+:\/\//)){return a}return tablet.__moduleBase+a}
function F(){var f=[];var g=V;function h(a,b){var c=f;for(var d=V,e=a.length-W;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(s){s(a,d,b)}throw null}
j[Kb]=function(){{var a=window.navigator.userAgent.toLowerCase();if(a.indexOf(Lb)!=-1||(a.indexOf(Mb)!=-1||(a.indexOf(Nb)!=-1||(a.indexOf(Ob)!=-1||a.indexOf(Pb)!=-1)))){var b=document.location.href;if(b.indexOf(Qb)===V||b.indexOf(Rb)===V){return Sb}}return Tb}};i[Kb]={'no':V,'yes':W};j[Ub]=function(){var a=navigator.userAgent.toLowerCase();var b=p.documentMode;if(function(){return a.indexOf(Vb)!=-1}())return Wb;if(function(){return a.indexOf(Xb)!=-1&&(b>=Yb&&b<Zb)}())return $b;if(function(){return a.indexOf(Xb)!=-1&&(b>=_b&&b<Zb)}())return ac;if(function(){return a.indexOf(Xb)!=-1&&(b>=bc&&b<Zb)}())return cc;if(function(){return a.indexOf(dc)!=-1||b>=Zb}())return ec;return bb};i[Ub]={'gecko1_8':V,'ie10':W,'ie8':fc,'ie9':gc,'safari':hc};__gwt_isKnownPropertyValue=function(a,b){return b in i[a]};tablet.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};tablet.__computePropValue=k;o.__gwt_activeModules[S].bindings=tablet.__getPropMap;r(O,ic);if(q()){return D(jc)}var l;try{h([Tb,ec],kc);h([Tb,$b],kc+lc);h([Tb,ac],kc+mc);h([Tb,Wb],kc+nc);h([Sb,Wb],kc+oc);l=f[k(Kb)][k(Ub)];var m=l.indexOf(pc);if(m!=-1){g=parseInt(l.substring(m+W),Yb);l=l.substring(V,m)}}catch(a){}tablet.__softPermutationId=g;return D(l+qc)}
function G(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}r(rc,P);r(rc,sc)}
B();tablet.__moduleBase=C();t[S].moduleBase=tablet.__moduleBase;var H=F();if(o){var I=!!(o.location.protocol==tc||o.location.protocol==uc);o.__gwt_activeModules[S].canRedirect=I;function J(){var b=vc;try{o.sessionStorage.setItem(b,b);o.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(I&&J()){var K=wc;var L=o.sessionStorage[K];if(!/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/.test(L)){if(L&&(window.console&&console.log)){console.log(xc+L)}L=bb}if(L&&!o[K]){o[K]=true;o[K+yc]=C();var M=p.createElement(jb);M.src=L;var N=p.getElementsByTagName(ob)[V];N.insertBefore(M,N.firstElementChild||N.children[V]);return false}}}G();r(O,sc);A(H);return true}
tablet.succeeded=tablet();