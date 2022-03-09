# pageIndicatorView

#### PageIndicator that can be used with ViewPager2
![KakaoTalk_Photo_2022-03-09-18-38-45](https://user-images.githubusercontent.com/47018460/157414714-713b82d9-582a-45d4-9263-333e003eaa6a.gif)


## Dependency 

```
implementation("io.github.goddb:pageindicatorview:1.0.0")
```

## How to Use

##### 1. You should do `clipChildren = false` to Parent ViewGroup

``` 
  <androidx.constraintlayout.widget.ConstraintLayout 
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:clipChildren="false">
    
    
  </androidx.constraintlayout.widget.ConstraintLayout>    
```     

##### 2. Use PageIndicatorView (You must layout_width, layout_height `WRAP_CONTENT`)

``` 
  <androidx.constraintlayout.widget.ConstraintLayout 
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:clipChildren="false">
    
        <com.godgod.pageindicator.PageIndicatorView
           android:id="@+id/cv_page_indicator"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           app:layout_constraintBottom_toBottomOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintStart_toStartOf="parent"
           android:layout_marginBottom="50dp"
           app:indicator_circle_radius="5dp"
           app:indicator_gap="4dp"
           app:indicator_type="worm"
           app:select_indicator_color="@color/white"
           app:unselect_indidator_color="@color/light_grey" />
    
  </androidx.constraintlayout.widget.ConstraintLayout>  

```

##### 3. ViewPager2 and PageIndicatorView can be connected. 

```
   viewPager2.connectTo(pageIndicator)
   pageIndicator.setIndicatorCount(listSize)

```

## PageIndicator Attributes 

##### PageIndicatorType.Basic

![KakaoTalk_Photo_2022-03-09-18-52-30 001](https://user-images.githubusercontent.com/47018460/157417236-31454aa2-ca17-45b5-9057-c18b32c5333f.gif)

##### PageIndicatorType.Fade

![KakaoTalk_Photo_2022-03-09-18-52-31 002](https://user-images.githubusercontent.com/47018460/157417528-4acbdff6-4b81-4314-875d-004e30db1dfa.gif)

##### PageIndicatorType.Jump

![KakaoTalk_Photo_2022-03-09-18-52-31 003](https://user-images.githubusercontent.com/47018460/157417611-00836230-4685-47a5-8b9a-f26b00ec9cb0.gif)

##### PageIndicatorType.Worm

![KakaoTalk_Photo_2022-03-09-18-52-32 004](https://user-images.githubusercontent.com/47018460/157417682-bc74a0d3-62e9-4699-a760-2435cae3c42f.gif)

##### PageIndicaotrType.ThinWorm

![KakaoTalk_Photo_2022-03-09-18-52-32 005](https://user-images.githubusercontent.com/47018460/157417828-5e5198f9-caf3-4c1b-99ba-cffa783a3edc.gif)

##### PageIndicatorType.Move

![KakaoTalk_Photo_2022-03-09-18-52-32 006](https://user-images.githubusercontent.com/47018460/157417910-0a652cb7-636e-495f-8688-39a74e7b6910.gif)


## License

```
MIT License

Copyright (c) 2022 GodDB

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

