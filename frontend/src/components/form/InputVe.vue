<template>
  <div class="form-group">
    <label for="id" v-if="label && labelPos === 'out'" class="form-label">{{
      label
    }}</label>
    <div :class="hasIcon(icon)">
      <span v-if="iconDir === 'left' && icon" class="input-group-text">
        <i :class="getIcon(icon)"></i>
        <span style="width: 5px"></span>
        <span v-if="labelPos === 'in'">{{ label }} </span>
      </span>
      <input
        :type="type"
        class="form-control"
        :class="getClasses(size, valid)"
        :name="name"
        :id="id"
        :value="value"
        :placeholder="placeholder"
        :isRequired="isRequired"
        :v-model="model"
      />
      <span v-if="iconDir === 'right' && icon" class="input-group-text">
        <span v-if="labelPos === 'in'" class="mr-3">
          {{ label }}
        </span>
        <span style="width: 5px"></span>

        <i :class="getIcon(icon)"></i>
      </span>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";
const props = defineProps({
  label: String,
  labelPos: {
    type: String,
    default: "out",
  },
  size: String,
  valid: Boolean,
  icon: String,
  iconDir: {
    type: String,
    default: "left",
  },
  name: String,
  id: String,
  value: String,
  placeholder: String,
  type: String,
  isRequired: Boolean,
  model: String,
});

function getClasses(size, valid) {
  let sizeValue, isValidValue;
  sizeValue = size ? `form-control-${size}` : null;
  isValidValue = valid ? `${valid}` : "invalid";
  return `${sizeValue} ${isValidValue}`;
}

function getIcon(icon) {
  return icon ? icon : null;
}
function hasIcon(icon) {
  return icon ? "input-group" : null;
}
</script>