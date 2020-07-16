var map = document.getElementById('map');
var score1 = document.getElementById('score1');
var score2 = document.getElementById('score2');
var begin = document.getElementById('begin');
var foodNumInput = document.getElementById('foodNum');
var timeInput = document.getElementById('time');
//使用构造方法创建snake对象
//属性有宽、高、默认走向、身体(body)，
//方法：显示，跑
function Snake(body, direction, scoreBox, speed) {
    //初始化属性
    this.width = 10; //单位宽度
    this.height = 10; //单位高度
    this.direction = direction;
    this.body = JSON.parse(JSON.stringify(body));
    this.score = 3;
    this.speed = speed;
    this.FastSpeed = false;
    this.FastSpeedRestore = false;
    //定义方法:
    //display方法的作用:1.用于第一次初始化贪吃蛇 2.每一次蛇run后根据body的每一个x，y更新相应的flag~
    this.display = function () {
        //把snake的body都显示出来，每一个node都绑定了一个flag(用于显示蛇node的一个包含各个参数的<div>)
        for (let i = 0; i < this.body.length; i++) {
            if (this.body[i].x != null) {//如果x==null,说明吃到了,body尾部多加了个空node，将在下一次run之后显示出来
                let node = document.createElement('div');
                this.body[i].flag = node;
                node.style.width = this.width + 'px';
                node.style.height = this.height + 'px';
                node.style.background = i === 0 ?"red":"green";
                node.style.borderRadius = "90%";
                node.style.position = 'absolute';
                node.style.left = this.body[i].x * this.width + 'px';
                node.style.top = this.body[i].y * this.height + 'px';
                map.appendChild(node);
            }
        }
    };
    this.run = function () {
        //通过snake的direction改变整个body的位置~
        //for循环用来改变除了头节点的其他节点位置
        for (let i = this.body.length - 1; i > 0; i--) {
            this.body[i].x = this.body[i - 1].x;
            this.body[i].y = this.body[i - 1].y;
        }
        //switch用来改变头节点位置
        switch (this.direction) {
            case "left":
                this.body[0].x -= 1;
                break;
            case "right":
                this.body[0].x += 1;
                break;
            case "up":
                this.body[0].y -= 1;
                break;
            case "down":
                this.body[0].y += 1;
                break;
        }
        //移动完之后要判断:1.是否越界(重开) 2.是否吃到了屎 3.是否吃到了自己的身体(重开)
        let Good = true;
        //1.判断蛇头是否越界
        if (this.body[0].x < 0 || this.body[0].x > 79 || this.body[0].y < 0 || this.body[0].y > 39) {
            Good = false;
        }
        // //3.如果吃到自己身体(4个node永不可能吃自己)
        // for (let i = 4; i < this.body.length; i++) {
        //     if (this.body[0].x == this.body[i].x && this.body[0].y == this.body[i].y) {
        //         Good = false;
        //         break;
        //     }
        // }
        if (!Good) {
            return false;  // 结束run函数
        }
        //2.吃到了屎,判断food.list的内容
        for (let i = 0; i < food.list.length; i++) {
            let foodX = food.list[i].x, foodY = food.list[i].y;
            if (this.body[0].x == foodX && this.body[0].y == foodY) {
                this.body.push({ x: null, y: null, flag: null });
                this.FastSpeed = food.list[i].flag.id === 'triangle-up';
                //这里用{x:null,y:null,flag:null}表示新增到body最后的节点,这个节点要等到下一次run之后才能被赋x,y的值并display出来
                map.removeChild(food.list[i].flag);
                food.display();
                food.list.splice(i, 1);
            }
        }
        //吃到了记得要更新score
        this.score = this.body.length;
        scoreBox.textContent = "得分:" + this.score;
        //判断完之后,如果没有越界/吃到自己,则要更新蛇body的位置
        //(先删除现在蛇body每个旧的flag，也就是旧的div，再display一下，也就是根据body的每个x,y实时更新div render的位置)
        for (let i = 0; i < this.body.length; i++) {
            if (this.body[i].flag != null) {
                map.removeChild(this.body[i].flag);
            }
        }
        this.display();
        return true;
    };

}
//food类 属性:宽，高
//方法 display
var SuperFoodIndex = 0;
function Food() {
    this.width = 10;
    this.height = 10;
    this.list = [];     //保存多个food node
    this.SuperFoodIndex = 0;
    this.display = function () {
        let node = document.createElement('div');
        this.flag = node;
        SuperFoodIndex++;
        if (SuperFoodIndex % 4 == 0) {
            node.id = "triangle-up";
        } else {
            node.style.width = this.width + 'px';
            node.style.height = this.height + 'px';
            node.style.background = "rgb(" + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + "," + Math.floor(Math.random() * 256) + ")";
            node.style.borderRadius = '80%';
        }
        node.style.position = 'absolute';
        this.x = Math.floor(Math.random() * 80);
        this.y = Math.floor(Math.random() * 40);
        node.style.left = this.x * this.width + 'px';
        node.style.top = this.y * this.width + 'px';
        map.appendChild(node);
        this.list.push({ x: this.x, y: this.y, flag: this.flag });
    };
};
let body1 = [
    { x: 2, y: 0, flag: 1 },
    { x: 1, y: 0, flag: 1 },
    { x: 0, y: 0, flag: 1 }
];
let body2 = [
    { x: 77, y: 0, flag: 1 },
    { x: 78, y: 0, flag: 1 },
    { x: 79, y: 0, flag: 1 }
];
var direction1 = "right";
var direction2 = "left";
var defaultSpeed = 250;
var snake1 = new Snake(body1, direction1, score1, defaultSpeed);
var snake2 = new Snake(body2, direction2, score2, defaultSpeed);
var food = new Food();
snake1.display();
snake2.display();
function createFood(num) {
    for (let i = 0; i < num; i++) {
        food.display();
    }
}
function InitialSnake(snake, body, direction) {
    for (let i = 0; i < snake.body.length; i++) {
        if (snake.body[i].flag != null) {
            map.removeChild(snake.body[i].flag);
        }
    }
    snake.speed=defaultSpeed;
    snake.FastSpeed=false;
    snake.status=false;
    snake.body = JSON.parse(JSON.stringify(body));
    snake.direction = direction;
    snake.display();  // 显示初始状态
}
function judgeMutualEat(s1, s2) {
    //规则：snake1的蛇头如果吃到snake2的蛇头，则游戏结束，如果吃到snake2的身体，则snake1的身体+1，snake2的身体-1
    let i;
    for (i = 0; i < s2.body.length; i++) {
        if (s1.body[0].x === s2.body[i].x && s1.body[0].y === s2.body[i].y) {
            break;
        }
    }
    if (i < s2.body.length) {
        s1.body.push({ x: null, y: null, flag: null });
        map.removeChild(s2.body[i].flag);
        s2.body.splice(i, 1);
    }
}
var timeOut=null;
function isFastSpeed(s, timer,Flagobj) {
    console.log(s.speed);
    if (s.FastSpeed&&timeOut===null) {
        s.FastSpeed=false;
        s.speed /= 2;
        timer = refreshInterval(timer, s,Flagobj);
        timeOut = setTimeout(function() {
             s.speed *= 2;
             s.FastSpeedRestore=true;
            }, 5000);
    }
    if(s.FastSpeedRestore){
        timeOut=null;
        s.FastSpeedRestore=false;
        timer = refreshInterval(timer, s, Flagobj);
    }
    return timer;
}
// 点击开始时，动起来
var timer1, timer2, timer3,timer4;
function refreshInterval(timer, s, Flagobj) {
    clearInterval(timer);
    timer = setInterval(function () {
        Flagobj.flag = s.run()===null?false:s.run();
        if (!Flagobj.flag) {
            clearInterval(timer);
        }
    }, s.speed);
    return timer;
}
function start() {
    let foodNum = Number(foodNumInput.value);
    if (begin.textContent === '开始' || begin.textContent === '重新开始')
        createFood(foodNum);
    let flag1 = {flag:true}, flag2 = {flag:true};
    timer1 = refreshInterval(timer1, snake1,  flag1);
    timer2 = refreshInterval(timer2, snake2,  flag2);
    setCountdown();
    timer3 = setInterval(function () {
        //flag3判断蛇头相遇问题, (x,y)的变化(由run函数决定)会比direction(由下一步 run函数决定)快一步，所以要从direction的那一步判断才行
        let flag3 = ((snake1.body[0].x - snake2.body[0].x) === 1 && snake1.body[0].y == snake2.body[0].y && snake1.direction === 'right' && snake2.direction === 'left'
        ) || ((snake2.body[0].x - snake1.body[0].x) === 1 && snake1.body[0].y == snake2.body[0].y && snake2.direction === 'right' && snake1.direction === 'left'
            ) || ((snake1.body[0].y - snake2.body[0].y) === -1 && snake1.body[0].x == snake2.body[0].x && snake1.direction === 'up' && snake2.direction === 'down'
            ) || ((snake2.body[0].y - snake1.body[0].y) === -1 && snake1.body[0].x == snake2.body[0].x && snake2.direction === 'up' && snake1.direction === 'down');

        if ((!flag1.flag && !flag2.flag) || (snake1.body[0].x === snake2.body[0].x && snake1.body[0].y === snake2.body[0].y) || flag3 || Number(timeInput.value)<=0 ) {
            clearInterval(timer1);
            clearInterval(timer2);
            clearInterval(timer3);
            clearInterval(timer4);
            timeInput.value="60";
            let win = snake1.score > snake2.score ? "恭喜玩家1赢拉~" : "恭喜玩家2赢拉~";
            if (snake1.score === snake2.score)
                win = "平局平局！~ 再整一把？";
            alert("game over," + win);
            begin.textContent = '重新开始';
        }
        //处理是否互相吃
        judgeMutualEat(snake1, snake2);
        judgeMutualEat(snake2, snake1);
        //处理是否吃到了加速屎：
        timer1 = isFastSpeed(snake1, timer1,flag1);
        timer2 = isFastSpeed(snake2, timer2,flag2);

    }, 100);
}
function cleanFoods() {
    for (let i = 0; i < food.list.length; i++) {
        map.removeChild(food.list[i].flag);
    }
    food.list = [];
}
function isNumber(value) {         //验证是否为数字
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else if (Number(value) > 0) {
        return true
    }
}
function setCountdown(){
    clearInterval(timer4);
    timer4=setInterval(function(){
        if(Number(timeInput.value)<=0){
            clearInterval(timer4);
        }else{
            timeInput.value=Number(timeInput.value)-1;
        }
    },1000);
}
begin.onclick = function () {
    //判断输入
    if (!isNumber(foodNumInput.value)||!isNumber(timeInput.value)) {
        alert("请输入大于0的数字");
        !isNumber(foodNumInput.value)?foodNum:timeInput.focus();
        return false;
    }
    if (begin.textContent === '开始' || begin.textContent === '重新开始') {
        clearInterval(timer1);
        clearInterval(timer2);
        clearInterval(timer3);
        clearInterval(timer4);
        cleanFoods();
        InitialSnake(snake1, body1, direction1);
        InitialSnake(snake2, body2, direction2);
        start();
        begin.textContent = '暂停';
    } else if (begin.textContent === '暂停') {
        clearInterval(timer1);
        clearInterval(timer2);
        clearInterval(timer3);
        clearInterval(timer4);
        begin.textContent = '继续';
    } else if (begin.textContent === '继续') {
        begin.textContent = '暂停';
        start();
    }
};
document.body.onkeydown = function (e) {
    // 有事件对象就用事件对象，没有就自己创建一个，兼容低版本浏览器
    var ev = e || window.event;
    switch (ev.keyCode) {
        case 38:
            if (snake2.direction != 'down') {  // 不允许返回，向上的时候不能向下
                snake2.direction = "up";
            }
            break;
        case 40:
            if (snake2.direction != "up") {
                snake2.direction = "down";
            }
            break;
        case 37:
            if (snake2.direction != "right") {
                snake2.direction = "left";
            }
            break;
        case 39:
            if (snake2.direction != "left") {
                snake2.direction = "right";
            }
            break;
        case 87:
            if (snake1.direction != 'down') {
                snake1.direction = "up";
            }
            break;
        case 83:
            if (snake1.direction != "up") {
                snake1.direction = "down";
            }
            break;
        case 65:
            if (snake1.direction != "right") {
                snake1.direction = "left";
            }
            break;
        case 68:
            if (snake1.direction != "left") {
                snake1.direction = "right";
            }
            break;
        case 32:
            clearInterval(timer1);
            clearInterval(timer2);
            clearInterval(timer3);
            begin.textContent = '继续';
            break;
    }
};