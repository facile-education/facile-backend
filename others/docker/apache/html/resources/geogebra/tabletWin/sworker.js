/* global self, caches*/

/**
* Designed by Gabor, made by GWT
*/
var urlsToCache = {"urls_to_cache":["https://cdn.geogebra.org/apps/5.0.344.0/web3d/5D68D1533E21C000E8640C909AD6B415.cache.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/clear.cache.gif",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/css/fonts.css",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/deferredjs/5D68D1533E21C000E8640C909AD6B415/1.cache.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/deferredjs/5D68D1533E21C000E8640C909AD6B415/2.cache.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/deferredjs/5D68D1533E21C000E8640C909AD6B415/3.cache.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/deferredjs/5D68D1533E21C000E8640C909AD6B415/4.cache.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/GeogebraSans-Regular.ttf",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/GeogebraSerif-Regular.ttf",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnbx10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnbxti10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnr10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnss10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnssbx10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnssi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wnti10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/cyrillic/fonts/jlm_wntt10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/base/jlm_cmex10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/base/jlm_cmmi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/base/jlm_cmmib10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/euler/jlm_eufb10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/euler/jlm_eufm10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_cmr10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmbi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmbx10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmr10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmsb10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmsbi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmsi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmss10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/jlm_jlmtt10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmbx10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmbxti10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmss10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmssbx10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmssi10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmti10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/latin/optional/jlm_cmtt10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_cmbsy10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_cmsy10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_msam10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_msbm10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_rsfs10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_special.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/jlm_stmary10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/fonts/maths/optional/jlm_dsrom10.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcmbipg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcmbpg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcmripg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcmrpg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcsbpg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcsropg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fcsrpg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/greek/fonts/jlm_fctrpg.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/font/mathsans.ttf",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/html/ggtcallback.html",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/html/opener.html",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/images/cursor_eraser.png",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/images/cursor_pen.png",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/js/webfont.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/js/workercheck.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/js/zipjs/deflate.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/js/zipjs/inflate.js",
"https://cdn.geogebra.org/apps/5.0.344.0/web3d/tabletWin.nocache.js",
"https://www.geogebra.org/graphing",
"https://www.geogebra.org/cas",
"https://www.geogebra.org/3d",
"https://www.geogebra.org/spreadsheet",
"https://www.geogebra.org/probability",
"https://www.geogebra.org/geometry",
"https://www.geogebra.org/exam",
 "https:\/\/ajax.googleapis.com\/ajax\/libs\/jquery\/1.11.1\/jquery.min.js",
                                    "https:\/\/ajax.googleapis.com\/ajax\/libs\/jqueryui\/1.11.1\/jquery-ui.min.js",
                                    "\/scripts\/jlatexmath\/jlatexmath.js?v=1472544409",
                                    "\/scripts\/deployggb.js?v=1472544409"],"unique_id":"#5.0.344.0:1576594912034"};

self.addEventListener('install', function(event) {
    "use strict";
    console.log("install");
    event.waitUntil(
        caches
            .open(urlsToCache.unique_id)
            .then(function(cache) {
                console.log('[install] Caches opened, adding GeoGebraWeb js files to cache');
                return cache.addAll(urlsToCache.urls_to_cache);
            })
            .then(function() {
                console.log('[install] All required resources have been cached');
            })
    );
});

self.addEventListener('fetch', function(event) {
    "use strict";
    event.respondWith(
        caches.match(event.request)
            .then(function(response) {
                if (response) {
                    return response;
                }
                return fetch(event.request);
            }).catch(function(reason) {
                    console.log(reason);
            })
    );
});

self.addEventListener('activate', function(event) {
    "use strict";

    console.log("activate");

    event.waitUntil(
        caches.keys()
            .then(function(cacheNames) {
                return Promise.all(
                    cacheNames.map(function(cacheName) {
                        if (urlsToCache.unique_id !== cacheName) {
                            console.log("deleting from cache " + cacheName);
                            return caches.delete(cacheName);
                        }
                    })
                );
            })
    );
});