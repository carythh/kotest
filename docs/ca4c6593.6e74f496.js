(window.webpackJsonp=window.webpackJsonp||[]).push([[67],{135:function(e,r,t){"use strict";t.r(r),t.d(r,"frontMatter",(function(){return i})),t.d(r,"metadata",(function(){return c})),t.d(r,"rightToc",(function(){return s})),t.d(r,"default",(function(){return l}));var n=t(3),a=t(7),o=(t(0),t(155)),i={title:"Test Ordering",slug:"test-ordering.html"},c={unversionedId:"framework/test_ordering",id:"framework/test_ordering",isDocsHomePage:!1,title:"Test Ordering",description:"When running multiple tests from a Spec, there's a certain order on how to execute them.",source:"@site/docs/framework/test_ordering.md",slug:"/framework/test-ordering.html",permalink:"/docs/framework/test-ordering.html",editUrl:"https://github.com/kotest/kotest/blob/master/documentation/docs/framework/test_ordering.md",version:"current",sidebar:"framework",previous:{title:"Spec Ordering",permalink:"/docs/framework/spec-ordering.html"},next:{title:"Grouping Tests with Tags",permalink:"/docs/framework/tags.html"}},s=[{value:"Sequential Ordering",id:"sequential-ordering",children:[]},{value:"Random Ordering",id:"random-ordering",children:[]},{value:"Lexicographic Ordering",id:"lexicographic-ordering",children:[]}],d={rightToc:s};function l(e){var r=e.components,t=Object(a.a)(e,["components"]);return Object(o.b)("wrapper",Object(n.a)({},d,t,{components:r,mdxType:"MDXLayout"}),Object(o.b)("p",null,"When running multiple tests from a Spec, there's a certain order on how to execute them."),Object(o.b)("p",null," By default, a ",Object(o.b)("strong",{parentName:"p"},"sequential")," order is used (order that tests are defined in the spec), but it's also possible to configure them\nto be executed in a ",Object(o.b)("strong",{parentName:"p"},"random")," order or ",Object(o.b)("strong",{parentName:"p"},"lexicographic")," order."),Object(o.b)("p",null,"This setting can be configured in either a ",Object(o.b)("inlineCode",{parentName:"p"},"Spec")," or in ",Object(o.b)("a",Object(n.a)({parentName:"p"},{href:"/docs/framework/project-config.html"}),"ProjectConfig")," by overriding the ",Object(o.b)("inlineCode",{parentName:"p"},"testCaseOrder")," function.\nIf both exist, the ",Object(o.b)("inlineCode",{parentName:"p"},"Spec"),"'s configuration will have priority."),Object(o.b)("div",{className:"admonition admonition-note alert alert--secondary"},Object(o.b)("div",Object(n.a)({parentName:"div"},{className:"admonition-heading"}),Object(o.b)("h5",{parentName:"div"},Object(o.b)("span",Object(n.a)({parentName:"h5"},{className:"admonition-icon"}),Object(o.b)("svg",Object(n.a)({parentName:"span"},{xmlns:"http://www.w3.org/2000/svg",width:"14",height:"16",viewBox:"0 0 14 16"}),Object(o.b)("path",Object(n.a)({parentName:"svg"},{fillRule:"evenodd",d:"M6.3 5.69a.942.942 0 0 1-.28-.7c0-.28.09-.52.28-.7.19-.18.42-.28.7-.28.28 0 .52.09.7.28.18.19.28.42.28.7 0 .28-.09.52-.28.7a1 1 0 0 1-.7.3c-.28 0-.52-.11-.7-.3zM8 7.99c-.02-.25-.11-.48-.31-.69-.2-.19-.42-.3-.69-.31H6c-.27.02-.48.13-.69.31-.2.2-.3.44-.31.69h1v3c.02.27.11.5.31.69.2.2.42.31.69.31h1c.27 0 .48-.11.69-.31.2-.19.3-.42.31-.69H8V7.98v.01zM7 2.3c-3.14 0-5.7 2.54-5.7 5.68 0 3.14 2.56 5.7 5.7 5.7s5.7-2.55 5.7-5.7c0-3.15-2.56-5.69-5.7-5.69v.01zM7 .98c3.86 0 7 3.14 7 7s-3.14 7-7 7-7-3.12-7-7 3.14-7 7-7z"})))),"note")),Object(o.b)("div",Object(n.a)({parentName:"div"},{className:"admonition-content"}),Object(o.b)("p",{parentName:"div"},"Nested tests will always run in discovery order (sequential) regardless of test ordering setting."))),Object(o.b)("h3",{id:"sequential-ordering"},"Sequential Ordering"),Object(o.b)("p",null,"Root tests are dispatched in the order they are defined in the spec file."),Object(o.b)("pre",null,Object(o.b)("code",Object(n.a)({parentName:"pre"},{className:"language-kotlin"}),'class SequentialSpec : StringSpec() {\n\n    override fun testCaseOrder(): TestCaseOrder? = TestCaseOrder.Sequential\n\n    init {\n      "foo" {\n        // I run first as I\'m defined first\n      }\n\n      "bar" {\n        // I run second as I\'m defined second\n      }\n    }\n}\n')),Object(o.b)("h3",{id:"random-ordering"},"Random Ordering"),Object(o.b)("p",null,"Root tests are dispatched in a random order."),Object(o.b)("pre",null,Object(o.b)("code",Object(n.a)({parentName:"pre"},{className:"language-kotlin"}),'class RandomSpec : StringSpec() {\n\n    override fun testCaseOrder(): TestCaseOrder? = TestCaseOrder.Random\n\n    init {\n      "foo" {\n        // This test may run first or second\n      }\n\n      "bar" {\n        // This test may run first or second\n      }\n    }\n}\n')),Object(o.b)("h3",{id:"lexicographic-ordering"},"Lexicographic Ordering"),Object(o.b)("p",null,"Root tests are dispatched in a lexicographic order."),Object(o.b)("pre",null,Object(o.b)("code",Object(n.a)({parentName:"pre"},{className:"language-kotlin"}),'class LexicographicSpec : StringSpec() {\n\n    override fun testCaseOrder(): TestCaseOrder? = TestCaseOrder.Lexicographic\n\n    init {\n      "foo" {\n        // I run second as bar < foo\n      }\n\n      "bar" {\n        // I run first as bar < foo\n      }\n    }\n}\n')))}l.isMDXComponent=!0},155:function(e,r,t){"use strict";t.d(r,"a",(function(){return p})),t.d(r,"b",(function(){return m}));var n=t(0),a=t.n(n);function o(e,r,t){return r in e?Object.defineProperty(e,r,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[r]=t,e}function i(e,r){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);r&&(n=n.filter((function(r){return Object.getOwnPropertyDescriptor(e,r).enumerable}))),t.push.apply(t,n)}return t}function c(e){for(var r=1;r<arguments.length;r++){var t=null!=arguments[r]?arguments[r]:{};r%2?i(Object(t),!0).forEach((function(r){o(e,r,t[r])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(r){Object.defineProperty(e,r,Object.getOwnPropertyDescriptor(t,r))}))}return e}function s(e,r){if(null==e)return{};var t,n,a=function(e,r){if(null==e)return{};var t,n,a={},o=Object.keys(e);for(n=0;n<o.length;n++)t=o[n],r.indexOf(t)>=0||(a[t]=e[t]);return a}(e,r);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(n=0;n<o.length;n++)t=o[n],r.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var d=a.a.createContext({}),l=function(e){var r=a.a.useContext(d),t=r;return e&&(t="function"==typeof e?e(r):c(c({},r),e)),t},p=function(e){var r=l(e.components);return a.a.createElement(d.Provider,{value:r},e.children)},u={inlineCode:"code",wrapper:function(e){var r=e.children;return a.a.createElement(a.a.Fragment,{},r)}},b=a.a.forwardRef((function(e,r){var t=e.components,n=e.mdxType,o=e.originalType,i=e.parentName,d=s(e,["components","mdxType","originalType","parentName"]),p=l(t),b=n,m=p["".concat(i,".").concat(b)]||p[b]||u[b]||o;return t?a.a.createElement(m,c(c({ref:r},d),{},{components:t})):a.a.createElement(m,c({ref:r},d))}));function m(e,r){var t=arguments,n=r&&r.mdxType;if("string"==typeof e||n){var o=t.length,i=new Array(o);i[0]=b;var c={};for(var s in r)hasOwnProperty.call(r,s)&&(c[s]=r[s]);c.originalType=e,c.mdxType="string"==typeof e?e:n,i[1]=c;for(var d=2;d<o;d++)i[d]=t[d];return a.a.createElement.apply(null,i)}return a.a.createElement.apply(null,t)}b.displayName="MDXCreateElement"}}]);