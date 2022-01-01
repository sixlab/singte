<#-- @ftlvariable name="status" type="java.lang.Integer" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>错误页面</title>
    <meta name="keywords" content="错误页面,singte"/>
    <meta name="description" content="singte 错误页面"/>
    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="bookmark" href="/favicon.ico">
    <link rel="apple-touch-icon" href="/static/touch-icon-apple.png">
    <script src="/static/plugins/jquery/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/static/css/normalize.css">
    <meta name="author" content="singte">
    <meta name="copyright" content="singte">
</head>
<body>
<script>
    "use strict";
    var _Math = Math;
    var _random = _Math.random;
    var atan2 = _Math.atan2;
    var cos = _Math.cos;
    var sin = _Math.sin;
    var hypot = _Math.hypot;
    var max = 200;
    var canvas = document.createElement("canvas");
    var $ = canvas.getContext('2d');
    var body = document.body;
    var particles = [];
    body.style.backgroundColor = "black";
    body.style.overflow = "hidden";
    body.appendChild(canvas);
    var width = canvas.width = window.innerWidth;
    var height = canvas.height = window.innerHeight;
    var point = {
        x: width / 2,
        y: height / 2
    };
    var hue = 0;

    function Particle() {
    }

    Particle.prototype = {
        init: function init() {
            this.hue = hue;
            this.alpha = 0;
            this.size = this.random(1, 5);
            this.x = this.random(0, width);
            this.y = this.random(0, height);
            this.velocity = this.size * .5;
            this.changed = null;
            this.changedFrame = 0;
            this.maxChangedFrames = 50;
            return this;
        },
        draw: function draw() {
            $.strokeStyle = "hsla(" + this.hue + ", 100%, 50%, " + this.alpha + ")";
            $.beginPath();
            $.arc(this.x, this.y, this.size, 0, 2 * Math.PI);
            $.stroke();
            this.update();
        },
        update: function update() {
            if (this.changed) {
                this.alpha *= .92;
                this.size += 2;
                this.changedFrame++;
                if (this.changedFrame > this.maxChangedFrames) {
                    this.reset();
                }
            } else if (this.distance(point.x, point.y) < 50) {
                this.changed = true;
            } else {
                var dx = point.x - this.x;
                var dy = point.y - this.y;
                var angle = atan2(dy, dx);
                this.alpha += .01;
                this.x += this.velocity * cos(angle);
                this.y += this.velocity * sin(angle);
                this.velocity += .02;
            }
        },
        reset: function reset() {
            this.init();
        },
        distance: function distance(x, y) {
            return hypot(x - this.x, y - this.y);
        },
        random: function random(min, max) {
            return _random() * (max - min) + min;
        }
    };

    function animate() {
        $.fillStyle = "rgba(0,0,0, .2)";
        $.fillRect(0, 0, width, height);
        particles.forEach(function (p) {
            p.draw();
        });
        hue += .3;
        window.requestAnimationFrame(animate);
    }

    function touches(e) {
        point.x = e.touches ? e.touches[0].clientX : e.clientX;
        point.y = e.touches ? e.touches[0].clientY : e.clientY;
    }

    function setup() {
        for (var i = 0; i < max; i++) {
            setTimeout(function () {
                var p = new Particle().init();
                particles.push(p);
            }, i * 10);
        }
        canvas.addEventListener("mousemove", touches);
        canvas.addEventListener("touchmove", touches);
        canvas.addEventListener("mouseleave", function () {
            point = {
                x: width / 2,
                y: height / 2
            };
        });
        window.addEventListener("resize", function () {
            width = canvas.width = window.innerWidth;
            height = canvas.height = window.innerHeight;
            point = {
                x: width / 2,
                y: height / 2
            };
        });
        animate();
    }

    setup();
</script>


<style>
    .st-error-msg-box {
        position: absolute;
        top: 150px;
        right: calc(50% - 200px);
        width: 400px;
        padding: 20px;

        z-index: 99998;

        background-color: rgba(255, 255, 255, 0.25);
        font-family: "pingfang SC", "helvetica neue", arial, "hiragino sans gb", "microsoft yahei ui", "microsoft yahei", simsun, sans-serif;
        font-size: 14px;

        transition: all .3s;
    }

    .st-error-msg-box .st-error-title {
        font-size: 24px;
        color: #d97403;
        text-align: center;
    }

    .st-error-msg-box .st-error-msg {
        margin: 10px 0;
        color: #ffffff;
    }

    .st-error-msg-box .st-error-link {
        width: 100%;
        text-align: center;
    }

    .st-error-msg-box .st-error-link a{
        color: #FFC000;
    }

    .st-error-footer {
        position: absolute;
        bottom: 0;
        width: 100%;
        text-align: center;
        color: wheat;
    }
</style>
<div class="st-error-msg-box">
    <h1 class="st-error-title">${status!'5002'}</h1>
    <div class="st-error-msg">${error!'页面处理异常'}</div>
    <div class="st-error-link">
        <a href="/">点击回首页</a>
    </div>
</div>


<!--footer-->
<div class="footer st-error-footer">
    <div class="w1200 clearfix">
        <div class="fl wechat">
            <img src="<@StConfig key="st_wx_qr" default="/static/images/wx/mp.jpg"/>" class="fl ml30">
            <div class="fl"><p class="p1">满茵科技</p>
                <p class="p2">singte官方微信</p></div>
        </div>
        <div class="clearfix fl copyright">
            <p class="p1">
            </p>
            <p class="mt40">©2021 满茵科技版权所有 </p>
        </div>
    </div>
</div>
</body>
</html>
