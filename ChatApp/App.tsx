import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import React from 'react';
import SignupScreen from './src/SignupScreen/SignupScreen';
import SigninScreen from './src/SigninScreen/SigninScreen';
import { RootStackParamList } from './src/types';
import {QueryClient, QueryClientProvider} from 'react-query';
import RootStack from './src/screens/RootStack';

const Stack = createNativeStackNavigator<RootStackParamList>();
const queryClient = new QueryClient();

const Screens = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <NavigationContainer>
        {/* <Stack.Navigator screenOptions={{ headerShown: false }}>
          <Stack.Screen name='Signup' component={SignupScreen} />
          <Stack.Screen name='Signin' component={SigninScreen} />
        </Stack.Navigator> */}
        <RootStack />
      </NavigationContainer>
    </QueryClientProvider>
  )
};

const App = () => {
  return <Screens />;
};

export default App;
