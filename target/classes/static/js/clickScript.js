(function() {
    var a_idx = 0;
    document.onclick = function(event) {
        var a = new Array("👻来拉？", "🐶给爷爬", "🐭嘤嘤嘤", "💩奥里给！~干了", "💨老屁王了","👨雷霆嘎巴","🐷哈拉少");
        var heart = document.createElement("b"); //bold粗体~
        heart.onselectstart = new Function('event.returnValue=false'); //不可选中

        document.body.appendChild(heart).innerHTML = a[a_idx]; //在heart后面加上字~
        a_idx = (a_idx + 1) % a.length; //循环遍历下一个字符串
        heart.style.cssText = "position: fixed;left:-100%;"; //定义heart的css

        var f = 16,
            x = event.clientX - f / 2, // left:
            y = event.clientY - f, // top:
            c = randomColor(), // 随机颜色
            a = 0.8, // 不透明度
            s = 1.2; // transform:scale  缩放

        var timer = setInterval(function() {
            if (a <= 0) { //这里是通过逐渐降低opacity（不透明度）属性来控制函数循环终点
                document.body.removeChild(heart);
                clearInterval(timer);
            } else {
                heart.style.cssText = "font-size:14px; font-weight:300;cursor: default;position: fixed;color:" + c + ";left:" + x + "px;top:" +
                    y + "px;opacity:" + a + ";transform:scale(" + s + ");";
                //每个循环改变css参数~
                y -= 0.3;
                x++;
                a -= 0.016;
                s -= 0.002;
            }
        }, 20);

    }
    // 返回随机颜色
    function randomColor() {
        return "rgb(" + (~~(Math.random() * 255)) + "," + (~~(Math.random() * 255)) + "," + (~~(Math.random() * 255)) +
            ")"; //~~符号它被用作一个更快的替代 Math.floor() 。
    }
}());