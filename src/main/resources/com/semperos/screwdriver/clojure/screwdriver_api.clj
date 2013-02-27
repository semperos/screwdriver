(ns screwdriver-api
  (:import [com.semperos.screwdriver Config]
           [java.io File]
           [java.util HashMap]))

(defn build-java-map
  [m]
  (let [h (HashMap.)]
    (doseq [[k v] m]
      (.put h (name k) v))
    h))

(defn build-config
  "Currently limited to needs of specific project"
  [{:keys [asset-directory output-directory
           css-dir-filter optimize-js?
           server-template-locals]}]
      (doto (Config.)
        (.setAssetDirectory        (File. asset-directory))
        (.setOutputDirectory       (File. output-directory))
        (.setCssDirFilter          css-dir-filter)
        (.setOptimizeJs            optimize-js?)
        (.setServerTemplateLocals  (build-java-map server-template-locals))))