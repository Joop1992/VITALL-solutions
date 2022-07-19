package com.example.vitallsolutions.model.beneficiaries.service;

import com.example.vitallsolutions.model.ModelAndViewInitializer;
import com.example.vitallsolutions.model.beneficiaries.Beneficiary;
import com.example.vitallsolutions.model.beneficiaries.crud.create.CreateBeneficiaryFormData;
import com.example.vitallsolutions.model.beneficiaries.crud.delete.DeleteBeneficiaryFormData;
import com.example.vitallsolutions.model.beneficiaries.crud.update.UpdateBeneficiaryFormData;
import com.example.vitallsolutions.model.beneficiaries.dto.BeneficiaryRecordDto;
import com.example.vitallsolutions.model.notification.Notification;
import com.example.vitallsolutions.model.notification.NotificationTypes;
import com.example.vitallsolutions.model.user.User;
import com.example.vitallsolutions.model.user.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
    public static final String NO_VALUE_SELECTED_IN_DROPDOWN = "0";
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ModelAndView createBeneficiaryRecord(@Valid @ModelAttribute("formData") CreateBeneficiaryFormData formData, BindingResult bindingResult) {
        ModelAndView beneficiariesView = this.getBeneficiariesPage();

        if (bindingResult.hasErrors()) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not add beneficiary record"));

            return this.getBeneficiariesPage();
        }

        if(formData.getUserId() == null || formData.getUserId().equals(NO_VALUE_SELECTED_IN_DROPDOWN) || formData.getBeneficiaryForUserID() == null || formData.getBeneficiaryForUserID().equals(NO_VALUE_SELECTED_IN_DROPDOWN)) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Please fill in all required fields"));

            return beneficiariesView;
        }

        if(!beneficiaryRepository.findByUserIdAndBeneficiaryForUserId(formData.toParameters().getUserId(), formData.toParameters().getBeneficiaryForUserId()).isEmpty()) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Beneficiary record already exists"));

            return beneficiariesView;
        }

        if(formData.toParameters().getUserId().equals(formData.getBeneficiaryForUserID())) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Beneficiary can't reference itself"));

            return beneficiariesView;
        }

        beneficiaryRepository.save(new Beneficiary(formData.toParameters().getUserId(), formData.toParameters().getBeneficiaryForUserId()));

        beneficiariesView = this.getBeneficiariesPage();
        beneficiariesView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Beneficiary record successfully created"));

        return beneficiariesView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteBeneficiaryRecord(@Valid @ModelAttribute("deleteFormData") DeleteBeneficiaryFormData formData, BindingResult bindingResult) {
        ModelAndView beneficiariesView = this.getBeneficiariesPage();

        if (bindingResult.hasErrors()) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not delete beneficiary record"));

            return this.getBeneficiariesPage();
        }

        if(!beneficiaryRepository.findById(formData.toParameters().getId()).isPresent()) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "No beneficiary record with this ID exists"));

            return beneficiariesView;
        }

        beneficiaryRepository.deleteById(formData.toParameters().getId());
        beneficiaryRepository.flush();

        beneficiariesView = this.getBeneficiariesPage();

        beneficiariesView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Beneficiary record successfully deleted"));

        return beneficiariesView;
    }

    @PostMapping("/edit")
    public ModelAndView editBeneficiaryRecord(@Valid @ModelAttribute("editFormData") UpdateBeneficiaryFormData formData, BindingResult bindingResult) {
        ModelAndView beneficiariesView = this.getBeneficiariesPage();

        if (bindingResult.hasErrors()) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not update beneficiary record"));

            return beneficiariesView;
        }

        if(formData.getPercentage() == null) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Percentage field can not be empty"));

            return beneficiariesView;
        }

        if(formData.toParameters().getPercentage() < 0.0) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Percentage for beneficiary can not be lower than 0.0%"));

            return beneficiariesView;
        }

        if(formData.toParameters().getPercentage() > 100.0) {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "Percentage for beneficiary can not be higher than 100.0%"));

            return beneficiariesView;
        }

        if(beneficiaryRepository.findById(formData.toParameters().getId()).isPresent()) {
            Optional<Beneficiary> be = beneficiaryRepository.findById(formData.toParameters().getId());

            if(be.isPresent()) {
                Beneficiary beneficiary = be.get();

                beneficiary.setPercentage(formData.toParameters().getPercentage());

                beneficiaryRepository.saveAndFlush(beneficiary);
                beneficiariesView = this.getBeneficiariesPage();
            }

            beneficiariesView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Beneficiary record successfully updated"));
        } else {
            beneficiariesView.addObject("notify", new Notification(NotificationTypes.INFO, "No beneficiary record with this ID exists"));

            return beneficiariesView;
        }

        return beneficiariesView;
    }

    private ModelAndView getBeneficiariesPage() {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();

        List<BeneficiaryRecordDto> records = new ArrayList<>();

        beneficiaryRepository.findAll().forEach(b -> {
            User user = userRepository.findById(b.getUserId()).get();
            User beneficiaryForUser = userRepository.findById(b.getBeneficiaryForUserId()).get();

            records.add(new BeneficiaryRecordDto(b.getId(), user, beneficiaryForUser, b.getPercentage()));
        });

        Map<String, String> userIdAndDisplayNameMap = new HashMap<>();

        userRepository.findAll().forEach(u -> {
            userIdAndDisplayNameMap.put(u.getId(), u.getName() + ", " + u.getEmail());
        });

        modelAndView.setViewName("/beneficiaries/view");
        modelAndView.addObject("beneficiaryRecords", records);
        modelAndView.addObject("users", userIdAndDisplayNameMap);
        modelAndView.addObject("formData", new CreateBeneficiaryFormData());
        modelAndView.addObject("deleteFormData", new DeleteBeneficiaryFormData());
        modelAndView.addObject("editFormData", new UpdateBeneficiaryFormData());
        modelAndView.addObject("selectedPage", "Beneficiaries");

        return modelAndView;
    }

    @RequestMapping(value="/view")
    public ModelAndView getPage() {
        return this.getBeneficiariesPage();
    }

}
