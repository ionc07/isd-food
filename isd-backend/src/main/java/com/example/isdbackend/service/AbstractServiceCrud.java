package com.example.isdbackend.service;

import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractServiceCrud {
    public final MenuRepository menuRepository;
    public final ProviderRepository providerRepository;
    public final OrderRepository orderRepository;
    public final UserRepository userRepository;

    public AbstractServiceCrud(MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.providerRepository = providerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
}
