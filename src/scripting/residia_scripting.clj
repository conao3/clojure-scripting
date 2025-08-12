(ns scripting.residia-scripting
  (:require
   [clojure.string :as str]
   [hickory.core :as hickory]
   [hickory.select :as s]
   [hickory.render :as hickory.render]
   [babashka.http-client :as http]))

(comment
  (letfn [(hiccup-extract-content-1 [el]
          (->> el
               (map (fn [e]
                      (cond
                        (keyword? e) nil
                        (map? e) nil
                        (string? e) e
                        (seqable? e) (hiccup-extract-content-1 e)
                        :else e)))))]
  (let [htree (-> "https://www.residia.jp/housing/housing-list/100/1"
                  http/get
                  :body
                  hickory/parse
                  hickory/as-hickory)
        property (->> htree
                      (s/select (s/and (s/class "col-lg") (s/class "bg-white")))
                      first)
        to-hiccup (comp hickory/as-hiccup
                        first
                        hickory/parse-fragment
                        hickory.render/hickory-to-html)
        hiccup-extract-content #(-> (->> % hiccup-extract-content-1 flatten (str/join ""))
                                    (str/replace #"\s+" " "))]
    ;; (->> property
    ;;      (s/select (s/tag :h3))
    ;;      first
    ;;      :content
    ;;      (str/join ""))
    ;; (->> property
    ;;      (s/select (s/tag :a))
    ;;      first
    ;;      :attrs
    ;;      :href)
    (->> property
         (s/select (s/tag :p))
         first
         to-hiccup
         hiccup-extract-content)))
"2003年01月 東京都港区三田１－１－１２ 1R～3LDK"
)
