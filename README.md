# 打造ListView万能适配器

```java
/**
 * 打造ListView、GridView万能适配器
 *
 * （一）
 * 解决ListView复用导致Item内容错乱的问题，解决方法：
 * 1、使用一个boolean值记录是否被复用
 * 2、当没有boolean值来记录是否被复用时，使用List集合来操作对应position的对象
 *
 * （二）
 * 某些会获取焦点的控件（如CheckBox、Button）作为Item的组件会抢占ListView的焦点，
 * 致使ListView不可点击，解决方案：
 * 1、（CheckBox、Button等）Item控件设置属性android:focusable="false"
 * 2、外层布局设置android:descendantFocusability="blocksDescendants"
*/
```

![](https://github.com/ykmeory/ListView_Adapter_Universal/blob/master/img.jpg "screenshot")
