using GUI.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Text.Json;
using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net;

namespace GUI.Controllers
{
    [Route("api/values")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        public string? Get()
        {
            var url = "http://54.165.116.235:8080/student/get-all";

            var request = WebRequest.Create(url);
            request.Method = "GET";

            using var webResponse = request.GetResponse();
            using var webStream = webResponse.GetResponseStream();

            using var reader = new StreamReader(webStream);
            var json_data = reader.ReadToEnd();

            var list = JsonSerializer.Deserialize<IList<StudentModel>>(json_data);
            var studentList = new List<StudentModel>();

            if (list != null)
            {
                foreach (var st in list)
                {
                    var student = new StudentModel();
                    student.id = st.id;
                    student.fullName = st.fullName;
                    student.gender = st.gender;
                    student.major = st.major;
                    student.birthday = st.birthday;
                    student.gpa = st.gpa;
                    studentList.Add(student);
                }
            }

            return studentList.ToString();
        }
    }
}
