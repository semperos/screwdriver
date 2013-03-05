(ns screwdriver-api
  (:require [clojure.template :as tpl])
  (:import [com.semperos.screwdriver Config]
           [java.io File]
           [java.util ArrayList HashMap]))

(defn java-map
  [m]
  (let [jh (HashMap.)]
    (doseq [[k v] m]
      (.put jh (name k) v))
    jh))

(defn java-list
  [l]
  (let [jl (ArrayList.)]
    (doseq [i l]
      (.add jl i))
    jl))

(defmacro ^{:private true} nn
  "Templating macro, if `pred` is not nil, it's used as a value for __ in the body forms."
  [pred & body]
  (let [body-with-param (tpl/apply-template '[__] body [pred])]
    `(when-not (nil? ~pred)
       ~@body-with-param)))

(defn build-config
  "Configure Screwdriver with a Clojure map. For advanced functionality like IOFileFilter's, an instance of that class is expected as the value of a map entry. For String values, or list of String values, default Clojure values may be used (e.g., vectors)."
  [{:keys [debug-mode asset-directory output-directory
           js-sub-directory-name css-sub-directory-name image-sub-directory-name
           template-sub-directory-name server-template-sub-directory-name
           static-asset-sub-directory-name
           js-extensions css-extensions image-extensions template-extensions
           server-template-extensions static-asset-extensions
           js-file-filter css-file-filter image-file-filter template-file-filter
           server-template-file-filter static-asset-file-filter
           js-dir-filter css-dir-filter image-dir-filter template-dir-filter
           server-template-dir-filter static-asset-dir-filter
           js-includes css-includes image-includes template-includes
           server-template-includes static-asset-includes
           js-excludes css-excludes image-excludes template-excludes
           server-template-excludes static-asset-excludes
           server-template-locals
           optimize-js? optimize-css? optimize-image?
           js-source-maps?
           rjs-modules ]}]
  (let [c (Config.)]
    (nn debug-mode
        (.setDebugMode c __))
    (nn asset-directory
        (.setAssetDirectory c __))
    (nn output-directory
        (.setOutputDirectory c __))
    (nn js-sub-directory-name
        (.setJsSubDirectoryName c __))
    (nn css-sub-directory-name
        (.setCssSubDirectoryName c __))
    (nn image-sub-directory-name
        (.setImageSubDirectoryName c __))
    (nn template-sub-directory-name
        (.setTemplateSubDirectoryName c __))
    (nn server-template-sub-directory-name
        (.setServerTemplateSubDirectoryName c __))
    (nn static-asset-sub-directory-name
        (.setStaticAssetSubDirectoryName c __))
    (nn js-extensions
        (.setJsExtensions c (java-list __)))
    (nn css-extensions
        (.setCssExtensions c (java-list __)))
    (nn image-extensions
        (.setImageExtensions c (java-list __)))
    (nn template-extensions
        (.setTemplateExtensions c (java-list __)))
    (nn server-template-extensions
        (.setServerTemplateExtensions c (java-list __)))
    (nn static-asset-extensions
        (.setStaticAssetExtesions c (java-list __)))
    (nn js-file-filter
        (.setJsFileFilter c __))
    (nn css-file-filter
        (.setCssFileFilter c __))
    (nn image-file-filter
        (.setImageFileFilter c __))
    (nn template-file-filter
        (.setTemplateFileFilter c __))
    (nn server-template-file-filter
        (.setServerTemplateFileFilter c __))
    (nn static-asset-file-filter
        (.setStaticAssetFileFilter c __))
    (nn js-dir-filter
        (.setJsDirFilter c __))
    (nn css-dir-filter
        (.setCssDirFilter c __))
    (nn image-dir-filter
        (.setImageDirFilter c __))
    (nn template-dir-filter
        (.setTemplateDirFilter c __))
    (nn server-template-dir-filter
        (.setServerTemplateDirFilter c __))
    (nn static-asset-dir-filter
        (.setStaticAssetDirFilter c __))
    (nn js-includes
        (.setJsIncludes c (java-list __)))
    (nn css-includes
        (.setCssIncludes c (java-list __)))
    (nn image-includes
        (.setImageIncludes c (java-list __)))
    (nn template-includes
        (.setTemplateIncludes c (java-list __)))
    (nn server-template-includes
        (.setServerTemplateIncludes c (java-list __)))
    (nn static-asset-includes
        (.setStaticAssetIncludes c (java-list __)))
    (nn js-excludes
        (.setJsExcludes c (java-list __)))
    (nn css-excludes
        (.setCssExcludes c (java-list __)))
    (nn image-excludes
        (.setImageExcludes c (java-list __)))
    (nn template-excludes
        (.setTemplateExcludes c (java-list __)))
    (nn server-template-excludes
        (.setServerTemplateExcludes c (java-list __)))
    (nn static-asset-excludes
        (.setStaticAssetExcludes c (java-list __)))
    (nn server-template-locals
        (.setServerTemplateLocals c (java-map __)))
    (nn optimize-js?
        (.setOptimizeJs c __))
    (nn optimize-css?
        (.setOptimizeCss c __))
    (nn optimize-image?
        (.setOptimizeImage c __))
    (nn js-source-maps?
      (.setJsSourceMapsEnabled c __))
    (nn rjs-modules
        (.setRjsModules c (java-list __)))
    c))