package com.example.jieyue.merchant.service;

import com.example.jieyue.common.entity.SysGoods;
import com.example.jieyue.common.entity.SysMt;
import com.example.jieyue.common.mapper.SysGoodsMapper;
import com.example.jieyue.common.utils.GiteeImgBedUtils;
import com.example.jieyue.common.utils.IsEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MerchantGoodsService {
    @Autowired
    SysGoodsMapper goodsMapper;
    @Autowired
    IsEmptyUtil isEmptyUtil = new IsEmptyUtil();
    
    /**
     * <p>获取全部商品</p>
     */
    public List<SysGoods> getAllGoods(){
        return goodsMapper.findAll();
    }

    /**
     * <p>通过id获取商品</p>
     */
    public SysGoods getGoodsById(int id){
        return goodsMapper.findById(id);
    }
    
    /**
     * <p>获取当前商户的商品列表</p>
     */
    public List<SysGoods> getMtGoods(HttpSession session,int page,int num){
        SysMt sysMt = (SysMt) session.getAttribute("merchant");
        List<SysGoods> list = goodsMapper.findByMtLimit(sysMt.getId(),(page-1)*num,num);
        for (SysGoods sysGoods : list) {
            sysGoods.setImg(GiteeImgBedUtils.PRE + sysGoods.getImg());
        }
        return list;
    }
    
    /**
     * <p>添加商品</p>
     * @return
     *-1 图片上传失败
     * 0 sql语句执行失败
     * 1 添加商品成功
     * 2 必填信息不能为空
     */
    public int addGoods(String name, String describe, BigDecimal price,MultipartFile img,
                        RedirectAttributes redirectAttributes,int stock,
                        HttpServletRequest request){
        if (isEmptyUtil.strings(name,describe)){
            return 2;
        }
        if (price == null){
            return 2;
        }
        int goodsMapperResult = 0;
        // 获取当前商户id
        SysMt sysMt = (SysMt)request.getSession().getAttribute("merchant");
        int merchant = sysMt.getId();

        if ("".equals(img.getOriginalFilename())){
            // 执行插入
            goodsMapperResult = goodsMapper.insert1(name,describe,price,merchant,stock);
            if(goodsMapperResult != 1){
                return 0;
            }
        }else{
            // 获取商户id，一个商户对应一个文件夹
            int id = ((SysMt) request.getSession().getAttribute("merchant")).getId();
            // 接收文件工具类返回的文件位置
            String imgUrl = GiteeImgBedUtils.upload("/data/goods/" + id, img);
            // 文件上传失败
            if (imgUrl == null){
                return -1;
            }
            // 执行插入
            goodsMapperResult = goodsMapper.insert2(name,describe,price,merchant,stock,imgUrl);
            if (goodsMapperResult != 1){
                // sql语句执行失败，将已上传的图片移除
                GiteeImgBedUtils.delete(imgUrl);
                return 0;
            }
        }
        return 1;
    }

    /**
     * <p>通过id值删除商品逻辑处理</p>
     * @return
     *-1 删除失败
     * 1 删除成功
     */
    public int delGoods(int id){
        // 获取goods示例图片的地址
        String imgUrl = goodsMapper.findById(id).getImg();
        // 删除数据库中goods的数据
        int sql = goodsMapper.deleteById(id);
        if (sql!=1){
            return -1;
        }else{
            // 删除源文件，与编译文件中对应的goods图片信息
            GiteeImgBedUtils.delete(imgUrl);
            return 1;
        }
    }

    /**
     * <p>获取总页数</p>
     */
    public int getAllPage(int num,SysMt merchant){
        return (int)Math.ceil((double)goodsMapper.goodsCount(merchant.getId())/(double)num);
    }

    /**
     * <p>上架商品</p>
     */
    public boolean putGoods(int goodId){
        return goodsMapper.updateState(goodId, 1) == 1;
    }

    /**
     * <p>下架商品</p>
     */
    public boolean OffGoods(int goodId){
        return goodsMapper.updateState(goodId, 0) == 1;
    }

    /**
     * <p>修改商品信息</p>
     * @return
     *-1 图片上传失败
     * 0 sql语句执行失败
     * 1 修改商品成功
     * 2 必填信息不能为空
     */
    public int updateGoods(String name, String describe, BigDecimal price,MultipartFile img,
                           RedirectAttributes redirectAttributes,int stock,
                           HttpServletRequest request,int goodsId){
        if (isEmptyUtil.strings(name,describe)){
            return 2;
        }
        if (price == null){
            return 2;
        }
        int goodsMapperResult = 0;
        // 获取当前商户id
        SysMt sysMt = (SysMt)request.getSession().getAttribute("merchant");
        int merchant = sysMt.getId();

        if (img.getOriginalFilename().equals("")){
            // 执行插入
            goodsMapperResult = goodsMapper.updateGoods1(name,describe,price,merchant,stock, goodsId);
            if(goodsMapperResult != 1){
                return 0;
            }
        }else{
            // 获取商户id，一个商户对应一个文件夹
            int id = ((SysMt) request.getSession().getAttribute("merchant")).getId();
            // 接收文件工具类返回的文件位置
            String imgUrl = GiteeImgBedUtils.upload("/data/goods/" + id, img);
            // 文件上传失败
            if (imgUrl == null){
                return -1;
            }
            // 执行插入
            SysGoods tempGoods = goodsMapper.findById(goodsId);
            goodsMapperResult = goodsMapper.updateGoods2(name,describe,price,merchant,stock,goodsId,imgUrl);
            if (goodsMapperResult != 1){
                // sql语句执行失败，将已上传的图片移除
                GiteeImgBedUtils.delete(imgUrl);
                return 0;
            }else{
                // 新图片已插入，将旧图删除
                GiteeImgBedUtils.delete(tempGoods.getImg());
            }
        }
        return 1;
    }
}
