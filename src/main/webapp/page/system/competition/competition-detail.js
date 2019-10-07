layui.use(['element', 'form', 'table', 'layer', 'laytpl', 'util'], function () {
    var $ = layui.$,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element, //Tab的切换功能，切换事件监听等，需要依赖element模块
        form = layui.form,
        laytpl = layui.laytpl,
        table = layui.table,
        util = layui.util;

    // 附件下载
    $("#getAnnex").click(function(){
        // var path =  '/home/soldier/SOLDIER'+$('#imgPreview').attr('src');
        var path =  $('#imgPreview').attr('src');
        var filename = '《'+ $('.itemName').val() +'》'+ $('.matchName').val() + '获奖图片.png';
        $.ajax({
            type: "POST",
            url: '/teacher_files_war/download/download_downloadFile.action',//数据接口
            data: {
                downloadPath: path,
                filename: filename
            }
        });
    });

});