import React from 'react';
import { View, Text } from 'react-native';

const Display = ({ result, lastResult }) => {
  return (
    <View className="flex-1 bg-black justify-end items-end px-4 pb-2">
      {lastResult ? (
        <Text className="text-gray-400 text-xl mb-1" numberOfLines={1}>
          {lastResult}
        </Text>
      ) : null}
      <Text className="text-white text-6xl font-light" numberOfLines={1}>
        {result}
      </Text>
    </View>
  );
};

export default Display;
