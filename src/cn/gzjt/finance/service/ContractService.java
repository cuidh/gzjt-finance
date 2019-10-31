package cn.gzjt.finance.service;

import cn.gzjt.finance.domain.Contract;
import cn.gzjt.finance.domain.PageBean;

/**
 * @author jianwei.zhou
 * @date 2019/10/15 14:48
 */
public interface ContractService {

    String addContract(Contract contract, String partyA, String partyB, String partyBCode, String projectKey);

    String updateContract(Contract contract, String partyA, String partyB, String partyBCode, String projectKey);

    String deleteContractByNumber(String number);

    PageBean<Contract> getContractsByKey(int curPage, int rowsOfOnePage, String key);
}
