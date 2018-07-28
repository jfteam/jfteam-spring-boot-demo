package org.jfteam.service.impl;

import org.jfteam.service.SecurityService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:20
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public String getPublicKey(String appId) {
        return "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJjMNnhwoen93pwnId6fm004LBPUYBl5Zl582aTPFCvxrSj5AlW7OYVlFw/sT0/xmLJ64nBuB5pV/DoiQVDYH+MCAwEAAQ==";
    }
}
