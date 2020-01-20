package project.neoroutes.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.neoroutes.server.domain.model.i.AddCertificate;
import project.neoroutes.server.domain.model.o.SuccessResult;
import project.neoroutes.server.service.CertificateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UserController {
    private final CertificateService certificateService;

    @Autowired
    public UserController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/certificates")
    public @ResponseBody
    SuccessResult addCertificate(@Valid @RequestBody AddCertificate addCertificate){
        certificateService.addCertificate(addCertificate);
        return SuccessResult.get();
    }
}
