(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{155:function(e,t,n){"use strict";n.d(t,"a",(function(){return u})),n.d(t,"b",(function(){return f}));var r=n(0),o=n.n(r);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function s(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var p=o.a.createContext({}),l=function(e){var t=o.a.useContext(p),n=t;return e&&(n="function"==typeof e?e(t):s(s({},t),e)),n},u=function(e){var t=l(e.components);return o.a.createElement(p.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},m=o.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,a=e.originalType,i=e.parentName,p=c(e,["components","mdxType","originalType","parentName"]),u=l(n),m=r,f=u["".concat(i,".").concat(m)]||u[m]||b[m]||a;return n?o.a.createElement(f,s(s({ref:t},p),{},{components:n})):o.a.createElement(f,s({ref:t},p))}));function f(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var a=n.length,i=new Array(a);i[0]=m;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s.mdxType="string"==typeof e?e:r,i[1]=s;for(var p=2;p<a;p++)i[p]=n[p];return o.a.createElement.apply(null,i)}return o.a.createElement.apply(null,n)}m.displayName="MDXCreateElement"},75:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return i})),n.d(t,"metadata",(function(){return s})),n.d(t,"rightToc",(function(){return c})),n.d(t,"default",(function(){return l}));var r=n(3),o=n(7),a=(n(0),n(155)),i={id:"proptestconfig",title:"Configuration",slug:"property-test-config.html"},s={unversionedId:"proptest/proptestconfig",id:"proptest/proptestconfig",isDocsHomePage:!1,title:"Configuration",description:"Configuration",source:"@site/docs/proptest/config.md",slug:"/proptest/property-test-config.html",permalink:"/docs/proptest/property-test-config.html",editUrl:"https://github.com/kotest/kotest/blob/master/documentation/docs/proptest/config.md",version:"current",sidebar:"proptest",previous:{title:"Generator Operations",permalink:"/docs/proptest/generator-operations.html"},next:{title:"Custom Generators",permalink:"/docs/proptest/custom-generators.html"}},c=[{value:"Configuration",id:"configuration",children:[{value:"Seed",id:"seed",children:[]},{value:"Min Failure",id:"min-failure",children:[]},{value:"PropTestListener",id:"proptestlistener",children:[]}]}],p={rightToc:c};function l(e){var t=e.components,n=Object(o.a)(e,["components"]);return Object(a.b)("wrapper",Object(r.a)({},p,n,{components:t,mdxType:"MDXLayout"}),Object(a.b)("h2",{id:"configuration"},"Configuration"),Object(a.b)("p",null,"Kotest provides for the  ability to specify some configuration options when running a property test. We do this by passing\nin an instance of ",Object(a.b)("inlineCode",{parentName:"p"},"PropTestConfig")," to the test methods."),Object(a.b)("p",null,"For example:"),Object(a.b)("pre",null,Object(a.b)("code",Object(r.a)({parentName:"pre"},{className:"language-kotlin"}),'class PropertyExample: StringSpec({\n   "String size" {\n      forAll<String, String>(PropTestConfig(options here...)) { a,b ->\n         (a + b).length == a.length + b.length\n      }\n   }\n})\n')),Object(a.b)("h3",{id:"seed"},"Seed"),Object(a.b)("p",null,"The most common configuration option is specifying the seed for the random instance. This is used when you want to\nreliably create the same values each time the test is run. You might want to do this if you find a test failure,\nand you want to ensure that that particular set of values continues to be executed in the future as a kind of regression\ntest."),Object(a.b)("div",{className:"admonition admonition-tip alert alert--success"},Object(a.b)("div",Object(r.a)({parentName:"div"},{className:"admonition-heading"}),Object(a.b)("h5",{parentName:"div"},Object(a.b)("span",Object(r.a)({parentName:"h5"},{className:"admonition-icon"}),Object(a.b)("svg",Object(r.a)({parentName:"span"},{xmlns:"http://www.w3.org/2000/svg",width:"12",height:"16",viewBox:"0 0 12 16"}),Object(a.b)("path",Object(r.a)({parentName:"svg"},{fillRule:"evenodd",d:"M6.5 0C3.48 0 1 2.19 1 5c0 .92.55 2.25 1 3 1.34 2.25 1.78 2.78 2 4v1h5v-1c.22-1.22.66-1.75 2-4 .45-.75 1-2.08 1-3 0-2.81-2.48-5-5.5-5zm3.64 7.48c-.25.44-.47.8-.67 1.11-.86 1.41-1.25 2.06-1.45 3.23-.02.05-.02.11-.02.17H5c0-.06 0-.13-.02-.17-.2-1.17-.59-1.83-1.45-3.23-.2-.31-.42-.67-.67-1.11C2.44 6.78 2 5.65 2 5c0-2.2 2.02-4 4.5-4 1.22 0 2.36.42 3.22 1.19C10.55 2.94 11 3.94 11 5c0 .66-.44 1.78-.86 2.48zM4 14h5c-.23 1.14-1.3 2-2.5 2s-2.27-.86-2.5-2z"})))),"tip")),Object(a.b)("div",Object(r.a)({parentName:"div"},{className:"admonition-content"}),Object(a.b)("p",{parentName:"div"},"Whenever a property test fails, Kotest will output the seed that was used. You can duplicate the test, setting it to use\nthis seed so you have permanent regression test for those values."))),Object(a.b)("p",null,"For example:"),Object(a.b)("pre",null,Object(a.b)("code",Object(r.a)({parentName:"pre"},{className:"language-kotlin"}),'class PropertyExample: StringSpec({\n   "String size" {\n      forAll<String, String>(PropTestConfig(seed = 127305235)) { a,b ->\n         (a + b).length == a.length + b.length\n      }\n   }\n})\n')),Object(a.b)("h3",{id:"min-failure"},"Min Failure"),Object(a.b)("p",null,"By default, Kotest tolerates no failure. Perhaps you want to run some non-deterministic test a bunch of times, and you're happy\nto accept some small number of failures. You can specify that in config."),Object(a.b)("pre",null,Object(a.b)("code",Object(r.a)({parentName:"pre"},{className:"language-kotlin"}),'class PropertyExample: StringSpec({\n   "some flakey test" {\n      forAll<String, String>(PropTestConfig(maxFailure = 3)) { a,b ->\n         // max of 3 inputs can fail\n      }\n   }\n})\n')),Object(a.b)("h3",{id:"proptestlistener"},"PropTestListener"),Object(a.b)("p",null,"Sometimes in property test it is required to perform some setup and tear down in each iteration of test.\nFor this purpose you can register a ",Object(a.b)("inlineCode",{parentName:"p"},"PropTestListener")," with ",Object(a.b)("inlineCode",{parentName:"p"},"PropTestConfig"),"."),Object(a.b)("pre",null,Object(a.b)("code",Object(r.a)({parentName:"pre"},{className:"language-kotlin"}),'class PropertyExample: StringSpec({\n   "some property test which require setup and tear down in each iteration" {\n      forAll<String, String>(PropTestConfig(listeners = listOf(MyPropTestListener))) { a,b ->\n         // some assertion\n      }\n   }\n})\n')))}l.isMDXComponent=!0}}]);