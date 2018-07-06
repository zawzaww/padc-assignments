package com.zawzaw.padc.asartaLineapp.delegates;

import com.zawzaw.padc.asartaLineapp.data.vos.WarDeeVO;

/**
 * Created by zawzaw on 06/07/2018.
 */

public interface WarDeeDelegate {

    void onTapWarDee(WarDeeVO warDee);

    void onSearch(String query);

    void onTapCategory(String category);

}
