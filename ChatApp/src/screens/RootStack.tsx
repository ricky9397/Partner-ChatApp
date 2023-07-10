import React from 'react';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {RootStackParamList} from './types';
// import MainTab from './MainTab';
// import ArticleScreen from './ArticleScreen';
import SigninScreen from '../SigninScreen/SigninScreen';
import SignupScreen from '../SignupScreen/SignupScreen';
// import MyArticlesScreen from './MyArticlesScreen';
// import useAuthLoadEffect from '../hooks/useAuthLoadEffect';
// import WriteScreen from './WriteScreen';

const Stack = createNativeStackNavigator<RootStackParamList>();

function RootStack() {
//   useAuthLoadEffect();

  return (
    <Stack.Navigator screenOptions={{headerBackTitle: '닫기'}}>
      {/* <Stack.Screen
        name="MainTab"
        component={MainTab}
        options={{headerShown: false}}
      />
      <Stack.Screen
        name="Article"
        options={{title: '게시글'}}
        component={ArticleScreen}
      /> */}
      <Stack.Screen
        name="Signin"
        component={SigninScreen}
        options={{title: '로그인'}}
      />
      <Stack.Screen
        name="Signup"
        component={SignupScreen}
        options={{title: '회원가입'}}
      />
      {/* <Stack.Screen
        name="MyArticles"
        component={MyArticlesScreen}
        options={{title: '내가 쓴 글'}}
      />
      <Stack.Screen
        name="Write"
        component={WriteScreen}
        options={{title: '새 게시글 작성'}}
      /> */}
    </Stack.Navigator>
  );
}

export default RootStack;