package com.example.vitallsolutions.model.client.service;

import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.model.ModelAndViewInitializer;
import com.example.vitallsolutions.model.client.Client;
import com.example.vitallsolutions.model.client.crud.create.CreateClientFormData;
import com.example.vitallsolutions.model.client.crud.delete.DeleteClientFormData;
import com.example.vitallsolutions.model.client.crud.update.UpdateClientFormData;
import com.example.vitallsolutions.model.notification.Notification;
import com.example.vitallsolutions.model.notification.NotificationTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    @PostMapping("/create")
    public ModelAndView createClient(@Valid @ModelAttribute("formData") CreateClientFormData formData, BindingResult bindingResult) {
        ModelAndView clientView = this.getClientPage();

        if (bindingResult.hasErrors()) {
            clientView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not add client"));

            return this.getClientPage();
        }

        Client c = new Client(
                formData.toParameters().getName(),
                formData.toParameters().getEmail(),
                formData.toParameters().getAddress(),
                userInfoProvider.getUserId(),
                formData.toParameters().getContactName());

                clientRepository.save(c);

        clientView = this.getClientPage();
        clientView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Client successfully created"));

        return clientView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteClient(@Valid @ModelAttribute("deleteFormData") DeleteClientFormData formData, BindingResult bindingResult) {
        ModelAndView clientView = this.getClientPage();

        if (bindingResult.hasErrors()) {
            clientView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not delete client"));

            return this.getClientPage();
        }

        if(!clientRepository.findByIdAndUserId(formData.getId(), userInfoProvider.getUserId()).isPresent()) {
            clientView.addObject("notify", new Notification(NotificationTypes.INFO, "No client with this ID exists"));

            return clientView;
        }

        clientRepository.deleteById(formData.getId());
        clientRepository.flush();

        clientView = this.getClientPage();

        clientView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Client successfully deleted"));

        return clientView;
    }

    @PostMapping("/edit")
    public ModelAndView editClient(@Valid @ModelAttribute("editFormData") UpdateClientFormData formData, BindingResult bindingResult) {
        ModelAndView clientView = this.getClientPage();

        if (bindingResult.hasErrors()) {
            clientView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not update client"));

            return clientView;
        }

        if(clientRepository.findByIdAndUserId(formData.getId(), userInfoProvider.getUserId()).isPresent()) {
            Optional<Client> cl = clientRepository.findById(formData.toParameters().getId());

            if(cl.isPresent()) {
                Client client = cl.get();

                client.setName(formData.toParameters().getName());
                client.setEmail(formData.toParameters().getEmail());
                client.setContactName(formData.toParameters().getContactName());
                client.setAddress(formData.toParameters().getAddress());

                clientRepository.saveAndFlush(client);
                clientView = this.getClientPage();
            }

            clientView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Client successfully updated"));

        } else {
            clientView.addObject("notify", new Notification(NotificationTypes.INFO, "No client with this ID exists"));

            return clientView;
        }

        return clientView;
    }
    
    private ModelAndView getClientPage() {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();

        modelAndView.setViewName("/clients/view");
        modelAndView.addObject("clients", clientRepository.findByUserId((userInfoProvider.getUserId())));
        modelAndView.addObject("formData", new CreateClientFormData());
        modelAndView.addObject("deleteFormData", new DeleteClientFormData());
        modelAndView.addObject("editFormData", new UpdateClientFormData());
        modelAndView.addObject("selectedPage", "Clients");

        return modelAndView;
    }

    @RequestMapping(value="/view")
    public ModelAndView getPage() {
        return this.getClientPage();
    }

}
