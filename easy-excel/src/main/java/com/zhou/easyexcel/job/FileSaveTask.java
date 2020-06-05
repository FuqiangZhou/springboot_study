package com.zhou.easyexcel.job;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vdurmont.emoji.EmojiParser;
import com.zhou.easyexcel.entity.FileSave;
import com.zhou.easyexcel.entity.UserAccount;
import com.zhou.easyexcel.entity.UserInfo;
import com.zhou.easyexcel.entity.UserMemberCard;
import com.zhou.easyexcel.mapper.FileSaveMapper;
import com.zhou.easyexcel.mapper.UserAccountMapper;
import com.zhou.easyexcel.mapper.UserInfoMapper;
import com.zhou.easyexcel.mapper.UserMemberCardMapper;
import com.zhou.easyexcel.util.DateUtils;
import com.zhou.easyexcel.util.IDUtils;
import com.zhou.easyexcel.vo.ImaginationCardVO;
import com.zhou.easyexcel.vo.StoredValueCardVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/10/10 5:51 下午
 */
@Component
public class FileSaveTask {

    private static final String EXCEL_PATH = "excels";

    @Resource
    private FileSaveMapper fileSaveMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserMemberCardMapper userMemberCardMapper;

    /**
     * 功能描述: 每月最后一天晚上11点进行统计
     * @author 周富强
     * @date 2019/10/10 5:58 下午
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void exportMemberCardInfo() throws IOException {
        Path imgPath = Paths.get(EXCEL_PATH);
        if (!Files.exists(imgPath)) {
            Files.createDirectories(imgPath);
        }
        String fileName = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()) + "用户会员卡信息.xlsx";
        FileSave fileSave = new FileSave();
        fileSave.setFileCode(IDUtils.getId());
        fileSave.setFileName(EXCEL_PATH + File.separator + fileName);
        fileSave.setCreateDate(new Date());
        fileSave.setCreateTime(new Date());
        this.fileSaveMapper.insert(fileSave);
        OutputStream os = Files.newOutputStream(Paths.get(EXCEL_PATH + File.separator + fileName));
        ExcelWriter excelWriter = EasyExcel.write(os).build();
        //储蓄卡
        List<UserAccount> userAccounts = this.userAccountMapper.selectList(new QueryWrapper<UserAccount>().eq("account_status", 1).orderByDesc("create_time"));
        List<StoredValueCardVO> storedValueCardVOS = userAccounts.stream().map(userAccount -> {
            StoredValueCardVO storedValueCardVO = new StoredValueCardVO();
            UserInfo userInfo = this.userInfoMapper.selectById(userAccount.getUserCode());
            if (userInfo != null){
                storedValueCardVO.setNickname(EmojiParser.parseToUnicode(userInfo.getNickname()));
                storedValueCardVO.setUserPhone(userInfo.getUserPhone());
                storedValueCardVO.setQuestionnaireItem(userInfo.getQuestionnaireItem());
                storedValueCardVO.setAccountBalance(userAccount.getAccountBalance());
            }
            return storedValueCardVO;
        }).collect(Collectors.toList());
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "储蓄卡").head(StoredValueCardVO.class).build();
        //畅想卡
        Date now = new Date();
        List<UserMemberCard> userMemberCards = this.userMemberCardMapper.selectList(new QueryWrapper<UserMemberCard>().eq("status", 1).orderByDesc("create_time"));
        List<ImaginationCardVO> imaginationCardVOS = userMemberCards.stream().map(userMemberCard -> {
            ImaginationCardVO imaginationCardVO = new ImaginationCardVO();
            UserInfo userInfo = this.userInfoMapper.selectById(userMemberCard.getUserCode());
            if (userInfo != null){
                imaginationCardVO.setNickname(EmojiParser.parseToUnicode(userInfo.getNickname()));
                imaginationCardVO.setUserPhone(userInfo.getUserPhone());
                imaginationCardVO.setQuestionnaireItem(userInfo.getQuestionnaireItem());
                imaginationCardVO.setValidityPeriod(userMemberCard.getValidityPeriod());
                long days = DateUtils.subDate(userMemberCard.getValidityPeriod(), now, DateUtils.TimeEnum.DAY);
                imaginationCardVO.setRemainingDays(days);
            }
            return imaginationCardVO;
        }).collect(Collectors.toList());
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "畅想卡").head(ImaginationCardVO.class).build();
        //开始写入
        excelWriter.write(storedValueCardVOS, sheet1);
        excelWriter.write(imaginationCardVOS, sheet2);
        excelWriter.finish();
        os.close();
    }


}
