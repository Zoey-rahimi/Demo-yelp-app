# Demo-yelp-app

This repository contains a sample app that implements MVVM architecture using Kotlin ,RxJava ,Retrofit , SwipeRefreshLayout.

1. The app has following packages:
  - model: It contains data classes and fetching data from remote API(API provider: https://www.yelp.com/developers)
  - view: View class along with its adapter.
  - viewModel: corresponding ViewModel for MainActivity

2. This app is using Yelp API to fetch data related to the Category "Coffee" where Location is "Sydney".

3. The app contains SwipeRefreshLayout in MainActivity which helps the user to refresh the contents of the view via a vertical swipe gesture.
