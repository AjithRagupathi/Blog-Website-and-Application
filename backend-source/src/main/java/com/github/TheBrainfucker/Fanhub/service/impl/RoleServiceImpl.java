package com.github.TheBrainfucker.Fanhub.service.impl;

import com.github.TheBrainfucker.Fanhub.model.Role;
import com.github.TheBrainfucker.Fanhub.service.RoleService;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService<Role> {

    // @Autowired
    // private RoleRepository roleRepository;

    // @Override
    // public Collection<Role> findAll() {
    // return roleRepository.findAll();
    // }

    // @Override
    // public Optional<Role> findById(Long id) {
    // return roleRepository.findById(id);
    // }

    // @Override
    // public Role findByName(String name) {
    // return roleRepository.findByName(name);
    // }

    // @Override
    // public Role saveOrUpdate(Role role) {
    // return roleRepository.saveAndFlush(role);
    // }

    // @Override
    // public String deleteById(Long id) {
    // JSONObject jsonObject = new JSONObject();
    // try {
    // roleRepository.deleteById(id);
    // jsonObject.put("message", "Role deleted successfully");
    // } catch (JSONException e) {
    // e.printStackTrace();
    // }
    // return jsonObject.toString();
    // }
}