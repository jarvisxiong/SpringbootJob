package com.stixcloud.patron.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.patron.constant.TitleConstant;
import com.stixcloud.patron.constant.PatronConstant;
import org.apache.commons.lang3.StringUtils;

@Component
public class MasterCodeTableUtil {


  public List<MasterCodeTable> getMasterCodeTable(String title, List<MasterCodeTable> titleMctList,
      List<MasterCodeTable> genderMctList) {
    List<MasterCodeTable> result = new ArrayList<MasterCodeTable>();
    MasterCodeTable mct = null;
    MasterCodeTable genderMct = null;
    List<String> lstTitleMale = Arrays.asList(TitleConstant.TitleMale);
    List<String> lstTitleFemale = Arrays.asList(TitleConstant.TitleFemale);
    if (!StringUtils.isEmpty(title)) {
      //Handle get titleMct by title input
      mct = titleMctList.stream().filter(p -> title.equals(p.getDescription2())).findFirst()
          .orElse(null);
      //Handle get genderMct by title input
      if (lstTitleMale.contains(title)) {
        genderMct = genderMctList.stream()
            .filter(
                p -> PatronConstant.MASTER_CODE_GENDER_MALE.getValue().equals(p.getDescription2()))
            .findFirst().orElse(null);
      } else if (lstTitleFemale.contains(title)) {
        genderMct = genderMctList.stream().filter(
            p -> PatronConstant.MASTER_CODE_GENDER_FEMALE.getValue().equals(p.getDescription2()))
            .findFirst().orElse(null);
      }
    }

    result.add(genderMct);
    result.add(mct);
    return result;
  }
}
