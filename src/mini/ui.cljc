(ns mini.ui
  (:require [clojure.test :as t :refer [deftest is]]))

(defn- year-picker [year]
  [:div
   [:div
    [:button {:on {:click [[:db/assoc :app/year 2024]]}}
     "2024"]
    [:button {:on {:click [[:db/assoc :app/year 2016]]}}
     "2016"]
    [:button {:on {:click [[:db/assoc :app/year 2017]]}}
     "2017"]
    [:button {:on {:click [[:db/assoc :app/year 1976]]}}
     "1976"]
    [:button {:on {:click [[:db/assoc :app/year 1901]]}}
     "1901"]
    [:button {:on {:click [[:db/assoc :app/year 1900]]}}
     "1900"]
    [:button {:on {:click [[:db/assoc :app/year 1899]]}}
     "1899"]
    [:button {:on {:click [[:db/assoc :app/year 2100]]}}
     "2100"]]
   [:div {:style {:margin-top "1em"}}
    [:button {:on {:click [[:db/update :app/year dec]]}}
     "<"]
    [:span.year
     " " year " "]
    [:button {:on {:click [[:db/update :app/year inc]]}}
     ">"]]])

(def month->name ["Jan" "Feb" "Mar" "Apr" "May" "June"
                  "July" "Aug" "Sept" "Oct" "Nov" "Dec"])

(defn- date-display [[month day]]
  [:span (month->name (dec month)) " " day])

(defn easter-day [year]
  [4 1])

(deftest test-easter-day
  (is (= [3 27] (easter-day 2016)))
  (is (= [4 16] (easter-day 2017)))
  (is (= [4 18] (easter-day 1976)))
  (is (= [4  7] (easter-day 1901)))
  (is (= [4 15] (easter-day 1900)))
  (is (= [4  2] (easter-day 1899)))
  (is (= [3 28] (easter-day 2100))))

(comment
  (t/run-test test-easter-day)
  :rcf)

(defn main-view [{:app/keys [year]}]
  [:div {:style {:position "relative"}}
   [:h1 "Easter Day Calculator 🐣"]
   (year-picker year)
   [:p "Easter day: "
    (date-display (easter-day year))]])