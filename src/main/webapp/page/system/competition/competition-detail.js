layui.use(['element', 'form', 'table', 'layer', 'laytpl', 'util'], function () {
    var $ = layui.$,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element, //Tab的切换功能，切换事件监听等，需要依赖element模块
        form = layui.form,
        laytpl = layui.laytpl,
        table = layui.table,
        util = layui.util;

});