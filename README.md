# Android 动画基础
## 第一章 课程介绍
### 1-1 课程介绍
1. 动画的意义
- 视觉效果

        优雅的动画能大幅度提升应用的观感,给用户带来良好的视觉感受.
- 引导用户

        好的动画能合理的吸引用户的注意力,起到引导用户的作用,让他们更轻松的理想我们应用的行为.
2. 课程目标
- 了解定义逐帧动画的方法
- 掌握定义视图动画的方法
- 掌握定义属性动画的方法
3. 学习内容
- 逐帧动画

        逐帧动画的基础是帧,也就是图片,这些图片一般由公司里的美工来制作,没有原图就没有办公应用逐帧动画,应用的范围就比较好.
- 视图动画系统
        
        视图动画系统操作的是视图对象,可以让他们透明度渐变,位移,旋转等效果. 视图已经可以定义丰富的动效了,但是它有局限性.
        通过对视图动画的学习我们可以掌握动画的基本属性.
- 属性动画系统
    
        属性动画系统操作的对象不再局限于视图,而且它可以真实的改变对象的属性.
        
 
## 第二章 逐帧动画
### 2-1 逐帧动画
1. 什么是逐帧动画
        
        逐帧动画又叫做图片动画,通过在一个固定区域一张一张的呈现事先准备好的一系列图片而产生动画效果.
2. 案例展示

![案例](/readme/img/a0.gif)

3. 实现方法
    -  使用AnimationDrawable来定义逐帧动画
    
    它是一个Drawable的容器,我们可以理解为它事先加载好了一系列图片.和普通的Drawable一样,它可以设置为视图对象的背景.
    最简单的定义逐帧的方法就是在xml文件中通过<Animation-list>来定义AnimationDrawable对象.

4. 关键实现步骤
    
   (1) 使用animation-list标签定义AnimationDrawable资源文件
   
```
    <?xml version="1.0" encoding="utf-8"?>
    <animation-list xmlns:android="http://schemas.android.com/apk/res/android" >
    <item android:drawable="@drawable/frame_1" android:duration="100" ></item>
    <item android:drawable="@drawable/frame_2" android:duration="100"></item>
    <item android:drawable="@drawable/frame_3" android:duration="100"></item>
    </animation-list>
```

   (2) 将这个资源文件设置给一个view的backgroud
   ```
         <Button
        android:id="@+id/btn_frame_animation"
        android:onClick="onClick"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="帧动画"
        />
   ```
   (3) 获得这个view的background强转成AnimationDrawable
   ```
        View view=findViewById(R.id.view);
        animationDrawable = (AnimationDrawable) view.getBackground();
   ```
   (4)通过AimationDrawable对象的start方法启动动画,使用stop方法停止动画
   ```
          public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_start:
                    animationDrawable.start();
                    break;
                case R.id.btn_stop:
                    animationDrawable.stop();
    
                    break;
            }
        }
   ```
  (5) 其他方法说明
  
        默认动画是循环播放的,如果想设置只播放一次
        
```
animationDrawable.setOneShot(true);//动一次
```
   
## 第三章 视图动画(重点)

### 3-1 视图动画-原理
       上次课咱们学习了逐帧动画定义的方法,逐帧动画的基础是图片文件,这些图片一般由公司里的美工制作,
       逐帧动画操作的对象就是这些美工制作的原图,没有原图就无法定义逐帧动画.
       
       这次课我们学习视图动画系统,和逐帧动画不同的是它操作的对象是Android中的视图对象,
       换句话说,通过视图动画系统我们能让视图对象.例如文本框,按钮,imageview等动起来.
       
       视图动画相关的关,都定义在这个包 **android.view.animation** 里面,
       
       其中有一个叫做Animation的抽象类,它有一些子类:
- AlphaAnimation
- ScaleAnimation
- TranslateAnimation
- RotateAnimation
- AnimationSet
        
        视图动画系统能够对视图对象展示补间动画,什么是补间呢,你给我一个起点,一个终点,
        再给我一个时长,我来计算在这期间需要做的一些变换.
        
        我们知道每个视图对象都有一个变换矩阵,用于把视图映射到手机屏幕上,对这个矩阵
        在单位时间内做不同的变换就可以产生运动效果.
        
        这就视图动画的原理
        
        后面我们会对Animation的子类,也就是这些能让视图产生动画效果的类进行逐一介绍.
### 3-2 视图动画-透明动画
1. 原理介绍
        
        和逐帧动画一样,我们可以在资源文件中定义视图动画,也可以在java文件中定义,如果是资源文件
        定义,我们使用alpha标签来定义视图动画,每个alpha标签都对应着一个AlphaAnimation对象,
        它是Animation的子类,它能控制对象透明程度.
2. 定义方法

    (1) 创建资源文件anim/alpha.xml
    ```
            <?xml version="1.0" encoding="utf-8"?>
            <set xmlns:android="http://schemas.android.com/apk/res/android">
            <alpha
                android:duration="1000"
                android:fromAlpha="1.0"
                android:toAlpha="0.1"
                />
            </set>
    ```
    (2) 使用AnimationUtils.loadAnimation方法加载动画资源文件
    ```
          Animation alphaAnimation=  AnimationUtils.loadAnimation(this,R.anim.alpha);
               
    ```
    (3) 调用视图对象的startAnimation方法执行动画
    ```
         tv_alpha.startAnimation(alphaAnimation);
    ```    
### 3-3 视图动画-缩放动画
1. 缩放动画简介
    在资源文件中使用scale标签来定义缩放动画,每个scale标签都会对应一个ScaleAnimation对象
    ScaleAimation和AlphaAnimation对象一样,同样是Animation中的子类 ,它能控制每一个视图在x轴或y轴的绽放
    程度.
2. 定义方法

(1) 定义动画资源文件anim/scale.xml      
```
<?xml version="1.0" encoding="utf-8"?>
        <set xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="1000"
        android:fillAfter="true">
        <scale
            android:fromXScale="1.0"
            android:toXScale="2.0"
            android:fromYScale="1.0"
            android:toYScale="2.0"
            android:pivotX="50%"
            android:pivotY="50%"
            />
        </set>
     
        <!--piotX 是基准点  50%代表基准点在控件的中间,  50%p代表基准点在父控件的中间-->
```
(2) 使用AnimationUtils.loadAnimation方法加载动画资源文件     
```
       Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);    
```   
(3)调用视图对象的startAnimation方法执行动画
```
         tv_scale.startAnimation(scaleAnimation);
```
### 3-4 视图动画-位移动画
1. 位移动画定义方法          
(1) 定义资源文件anim/transation.xml           
```
    <?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:fillAfter="true"
    >
    <translate
        android:fromXDelta="0"
        android:fromYDelta="0"
        android:toXDelta="50%p"
        android:toYDelta="0"
        />
    <!--50%是相对于原点右移动控件宽度一半,50%是相对于原点右移动父控件的中间
    android:repeatCount="1" 设置重复的次数    android:repeatCount="infinite" 表示无限次
       android:repeatMode="reverse" 重复模式 来回,restart是每次从原点
    -->
</set>
```
(2) 使用AnimationUtils.loadAnimation方法加载动画            

(3) 使用view.startAnimation(transationAnimation.startAnimation启动动画)           


### 3-5 视图动画-旋转动画
1. 定义旋转动画的方法            
(1)定义资源文件 anim/rotate.xml       
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="1000"
    android:fillAfter="true"
    >
<rotate
    android:fromDegrees="0"
    android:toDegrees="360"
    android:repeatCount="3"
    android:pivotY="50%"
    android:pivotX="50%"
    />
    <!--
    android:pivotY="50%"
    android:pivotX="50%"
    设置基准点在中间,默认在左上角
-->

</set>
```
(2)使用AnimationUtils.loadAnimation加载动画       
(3)调用view.startAnimation方法开启动画
### 3-6 视图动画-集合动画
1. 定义旋转动画的方法            
(1)定义资源文件 anim/set.xml       
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:fillAfter="true"

    >
    <rotate
        android:duration="1000"
        android:fromDegrees="0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toDegrees="360" />
    <translate
        android:duration="1000"
        android:fromXDelta="0"
        android:fromYDelta="0"
        android:startOffset="1000"
        android:toXDelta="90%p"
        android:toYDelta="0" />

    <!--startOffset是设置在一个动画完成后再执行第二个动画-->
</set>
```
(2)使用AnimationUtils.loadAnimation加载动画       
(3)调用view.startAnimation方法开启动画
### 3-7 选择练习

### 3-8 视图动画-插值器
1. 介绍       
大家回想一下刚才讲到的那些动画,是不是都是在一定时间内,某一个值发生了变化呢?
以位移动画为例,就跟我们人在10秒钟移动10米是一样的,起始值是0,目标值是10,然后我们在十秒钟完成这个移动
这就涉及到一个问题,我们以怎么样的速率移动呢?

有线性的移动(匀速),也可是一开始慢,后来快,或者一开始快,后来慢,这就是动画涉及到的一个变化率的问题
我们在定义动画时,可以为它指定变化率,来呈现不同的运动效果.

插值器用于设置动画的变化速率.     
2. 代码实现     
(1)添加两个视图viewAccelerate,viewLinear          
```
<View
            android:id="@+id/viewAcclerate"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:background="@color/colorPrimary"
            android:onClick="onClick"
            android:layout_marginTop="10dp"
            />

        <View
            android:id="@+id/viewLinear"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:background="@color/colorAccent"
            android:onClick="onClick"
            android:layout_marginTop="10dp"
            />
```
![插值器效果图](/readme/img/a3.png)
(2)使用AnimationUtils.loadAnimation方法加载动画translate     
分别设置一个AccelerateInterceptor和一个LinearInterceptor插值器      
(3)启动两个动画   
```
    case R.id.viewLinear:
                View view1=findViewById(R.id.viewAcclerate);
                View view2=findViewById(R.id.viewLinear);

                Animation animationAccelerate=AnimationUtils.loadAnimation(this,R.anim.translate);
                Animation animationLinear=AnimationUtils.loadAnimation(this,R.anim.translate);

                animationAccelerate.setInterpolator(new AccelerateInterpolator());
                animationLinear.setInterpolator(new LinearInterpolator());

                //启动
                view1.startAnimation(animationAccelerate);
                view2.startAnimation(animationLinear);

                break;
```
## 第四章 属性动画(重难点)
### 4-1 属性动画-原理
1. 视图动画 vs 属性动画     
(1)操作对象         
        a. 视图动画只能操作视图对象,也就那些文本框控件,按钮控件,当然也可以是viewGroup(比如说LinearLayout等)
        但是它不能操作其他的对象.       
     ****   b. 而属性动画框架它就可以操作任意的对象.
        回顾之前动画的本质中什么,就是你给我一个起始值,一个结果值,我能够让它平滑从起始值变化到结束值.        
        那这就是一个动画系统了,在这个变化过程中,可以是变速也可以是匀速的.       
        属性动画框架呢就可以操作任意对象,比如一个整型数,初始值0,结束值100,属性动画就可以控制这个整数值
        在一定的时间内从0变化到100,这是视图动画做不到的.因为视图动画操作的对象是一个view.      
(2)属性       
    **a.** 视图动画并不没有真正改变视图的属性,以缩放效果为例,让它在x轴上缩放2倍,它是有了这样一个缩放的效果            
    但当它缩完成后你去获得视图的宽度这个属性是没有真的变化的.      
   **b.**  而属性动画就不一样了,属性是真正能改变属性的.可以说相当视图动画,属性动画更为强大,视图动画能实现的那些     
    效果,属性动画都能实现.        
    那是不是之前我们学习的视图动画就白学了呢?
    并不是这样的,动画框架有变,甚至平台不同,但是动画的原理是不变的,动画的这些基础知识我们可以应用其他平台
    ,比如说javaScript,只不过它的实现方式是和我们的属性动画更为接近的.因为它是不断的改变网页上元素的属性.       
   **c.   现在我们来看一下通过属性动画框架我们能定义哪些动画属性**       
   - 我们可以定义动画的**时长**.      
   - 我们可以定义时间**插值器.**
   - 允许我们定义动画的**重复次数以及重复模式**
   - 允许我们定义**动画集**
   - 我们还可以定义动画的**延迟**       
    

### 4-2 属性动画-ValueAnimator

### 4-3 属性动画-ObjectAnimator
### 4-4 选择练习
### 4-5 属性动画-ViewPropertyAnimator
### 4-6 属性动画-AnimatorSet
### 4-7 选择练习
## 第五章 课程总结
### 5-1 课程总结